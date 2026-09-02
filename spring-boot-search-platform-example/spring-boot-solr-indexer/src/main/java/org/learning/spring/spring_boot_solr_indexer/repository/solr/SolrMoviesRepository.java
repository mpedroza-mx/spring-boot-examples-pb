package org.learning.spring.spring_boot_solr_indexer.repository.solr;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.jetty.HttpJettySolrClient;
import org.apache.solr.client.solrj.request.ContentStreamUpdateRequest;
import org.apache.solr.client.solrj.response.UpdateResponse;

import org.apache.solr.common.util.ContentStreamBase;
import org.learning.spring.spring_boot_solr_indexer.embebings.EmbeddingsGenerator;
import org.learning.spring.spring_boot_solr_indexer.entity.solr.MovieSolrEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;


import java.io.IOException;
import java.util.List;

@Component
public class SolrMoviesRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(SolrMoviesRepository.class);
    private final HttpJettySolrClient httpJettySolrClient;
    private final ObjectMapper objectMapper;

    public SolrMoviesRepository(HttpJettySolrClient httpJettySolrClient, ObjectMapper objectMapper) {
        this.httpJettySolrClient = httpJettySolrClient;
        this.objectMapper = objectMapper;
    }

    public Flux<UpdateResponse> sendDocuments(List<MovieSolrEntity> movieSolrEntities) throws SolrServerException, IOException {
        LOGGER.info("Sending document");

        String moviesSolrJsonPayload = objectMapper.writeValueAsString(movieSolrEntities);
        LOGGER.debug("JSON Payload: {}", moviesSolrJsonPayload);

        ContentStreamUpdateRequest updateRequest = new ContentStreamUpdateRequest("/update/json/docs");
        updateRequest.addContentStream(new ContentStreamBase.StringStream(moviesSolrJsonPayload, "application/json"));
        LOGGER.info("SOLR URL {}", httpJettySolrClient.getBaseURL());
        updateRequest.process(httpJettySolrClient);

        return Flux.just(httpJettySolrClient.commit());


    }
}
