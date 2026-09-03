package org.learning.spring.spring_boot_solr_indexer.embeddings;

import org.learning.spring.spring_boot_solr_indexer.entity.ollama.EmbeddingRequest;
import org.learning.spring.spring_boot_solr_indexer.entity.ollama.EmbeddingResponse;
import org.learning.spring.spring_boot_solr_indexer.entity.solr.MovieSolrEntity;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;


@Component
@ConditionalOnProperty(name = "app.semantic_search_enabled", havingValue = "true")
public class EmbeddingsGenerator {
    private final WebClient ollamaWebClient;
    private static final String MODEL = "nomic-embed-text";

    public EmbeddingsGenerator(WebClient ollamaWebClient) {
        this.ollamaWebClient = ollamaWebClient;
    }

    public Mono<List<MovieSolrEntity>> addEmbeddings(List<MovieSolrEntity> movieSolrEntities) {

        return ollamaWebClient.post()
                .uri("api/embed")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(buildEmbeddingRequest(movieSolrEntities))
                .retrieve()
                .bodyToMono(EmbeddingResponse.class)
                .map(response->{
                    List<List<Float>> embeddings = response.getEmbeddings();
                    for (int index=0; index<embeddings.size();index++){
                        movieSolrEntities.get(index).setVectors(embeddings.get(index));
                    }
                    return  movieSolrEntities;
                });
    }

    private EmbeddingRequest buildEmbeddingRequest(List<MovieSolrEntity> movieSolrEntityList) {
        return EmbeddingRequest.builder()
                .model(MODEL)
                .input(movieSolrEntityList.stream()
                        .map(movieSolrEntity -> {
                            StringBuffer template= new StringBuffer("");
                            if (movieSolrEntity.getTitle() != null && !movieSolrEntity.getTitle().isEmpty()){
                                template.append("Tittle: ")
                                        .append(movieSolrEntity.getTitle());
                            }
                            if (movieSolrEntity.getFullPlot() != null && !movieSolrEntity.getFullPlot().isEmpty()){
                                template.append("Full Plot: ")
                                        .append(movieSolrEntity.getFullPlot());
                            }
                            if (movieSolrEntity.getGenres() != null && !movieSolrEntity.getGenres().isEmpty()){
                                template.append("Genres: ")
                                        .append(String.join(", ", movieSolrEntity.getGenres()));
                            }

                            if (movieSolrEntity.getCast() != null && !movieSolrEntity.getCast().isEmpty()){
                                template.append("Cast: ")
                                        .append(String.join(", ", movieSolrEntity.getCast()));
                            }

                           return template.toString();
                        })
                        .collect(Collectors.toUnmodifiableList()))
                .build();
    }
}
