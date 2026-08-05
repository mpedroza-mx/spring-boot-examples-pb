package org.learning.spring.spring_boot_solr_indexer.importer;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.learning.spring.spring_boot_solr_indexer.entity.solr.MovieSolrEntity;
import org.learning.spring.spring_boot_solr_indexer.mapper.MovieMapper;
import org.learning.spring.spring_boot_solr_indexer.repository.mongo.MovieRepository;
import org.learning.spring.spring_boot_solr_indexer.repository.solr.SolrMoviesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class InitialDataSolrImport implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(InitialDataSolrImport.class);

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;
    private final SolrMoviesRepository solrMoviesRepository;


    public InitialDataSolrImport(MovieRepository movieRepository, MovieMapper movieMapper, SolrMoviesRepository solrMoviesRepository) {
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
        this.solrMoviesRepository = solrMoviesRepository;
    }

    @Override
    public void run(String... args) {
        LOGGER.info("Start importing data in solr");

        List<MovieSolrEntity> movieSolrEntities = movieRepository.findAll()
                .stream()
                .map(movieMapper::toMovieSolrEntity)
                .collect(Collectors.toUnmodifiableList());
        LOGGER.info("Total of documents to import {}", movieSolrEntities.size());
        try {

            UpdateResponse updateResponse = solrMoviesRepository.sendDocuments(movieSolrEntities);
            if (updateResponse.getStatus() != 0) {
                throw new RuntimeException("Error while trying to add the documents");
            }
        } catch (SolrServerException e) {
            LOGGER.info(e.getMessage(), e);
            throw new RuntimeException(e);
        } catch (IOException e) {
            LOGGER.info(e.getMessage(), e);
            throw new RuntimeException(e);
        }


    }
}
