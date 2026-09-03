package org.learning.spring.spring_boot_solr_indexer.importer;

import org.learning.spring.spring_boot_solr_indexer.config.AppProperties;
import org.learning.spring.spring_boot_solr_indexer.embebings.EmbeddingsGenerator;
import org.learning.spring.spring_boot_solr_indexer.entity.solr.MovieSolrEntity;
import org.learning.spring.spring_boot_solr_indexer.mapper.MovieMapper;
import org.learning.spring.spring_boot_solr_indexer.repository.mongo.MovieRepository;
import org.learning.spring.spring_boot_solr_indexer.repository.solr.SolrMoviesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Component
public class InitialDataSolrImport implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(InitialDataSolrImport.class);
    private static final int KEY_WORD_SEARCH_BATCH_SIZE = 10000;
    private static final int SEMANTIC_SEARCH_BATCH_SIZE = 1000;
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;
    private final SolrMoviesRepository solrMoviesRepository;
    private final Optional<EmbeddingsGenerator> optEmbeddingsGenerator;
    private final AppProperties appProperties;

    public InitialDataSolrImport(MovieRepository movieRepository, MovieMapper movieMapper, SolrMoviesRepository solrMoviesRepository, Optional<EmbeddingsGenerator> optEmbeddingsGenerator, AppProperties appProperties) {
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
        this.solrMoviesRepository = solrMoviesRepository;
        this.optEmbeddingsGenerator = optEmbeddingsGenerator;
        this.appProperties = appProperties;
    }

    @Override
    public void run(String... args) {
        LOGGER.info("Start importing data in solr");

        List<MovieSolrEntity> movieSolrEntities = movieRepository.findAll().stream()
                .map(movieMapper::toMovieSolrEntity)
                .collect(Collectors.toList());
        Flux<List<MovieSolrEntity>> fluxMovies;
        LOGGER.info("Semantic Search Enabled: {}", appProperties.isSemanticSearchEnabled());
        if (appProperties.isSemanticSearchEnabled()) {
            EmbeddingsGenerator embeddingsGenerator = optEmbeddingsGenerator.get();
            fluxMovies = Flux.fromIterable(movieSolrEntities.subList(0, 2000))
                    .buffer(SEMANTIC_SEARCH_BATCH_SIZE)
                    .concatMap(embeddingsGenerator::addEmbeddings);

        } else {
            fluxMovies = Flux.fromIterable(movieSolrEntities)
                    .buffer(KEY_WORD_SEARCH_BATCH_SIZE);
        }
        fluxMovies.concatMap(batch -> Mono.fromCallable(() -> solrMoviesRepository.sendDocuments(batch)))
                .flatMap(flux -> flux)
                .subscribe(updateResponse -> LOGGER.info("Solr Update Response: {}", updateResponse.getStatus()), error -> LOGGER.error(error.getMessage(), error));
    }
}
