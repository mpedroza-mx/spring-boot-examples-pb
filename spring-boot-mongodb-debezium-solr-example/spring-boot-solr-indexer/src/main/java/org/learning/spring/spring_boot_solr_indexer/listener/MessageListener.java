package org.learning.spring.spring_boot_solr_indexer.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.learning.spring.spring_boot_solr_indexer.mapper.MovieMapper;
import org.learning.spring.spring_boot_solr_indexer.repository.solr.SolrMoviesRepository;
import org.learning.spring.spring_boot_solr_indexer.entity.mongo.MovieEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

@Component
public class MessageListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageListener.class);
    private final SolrMoviesRepository solrMoviesRepository;
    private final MovieMapper movieMapper;
    private final ObjectMapper objectMapper;

    public MessageListener(SolrMoviesRepository solrMoviesRepository, MovieMapper movieMapper, ObjectMapper objectMapper) {
        this.solrMoviesRepository = solrMoviesRepository;
        this.movieMapper = movieMapper;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(id = "spring-boot-solr-indexer",
            topics = "${app.kafka-topic}")
    public void consumeMessage(ConsumerRecord<String, String> record) throws SolrServerException, IOException {
        LOGGER.info("Record message: {}", record.toString());
        UpdateResponse updateResponse = solrMoviesRepository.sendDocuments(List.of(movieMapper.toMovieSolrEntity(objectMapper.readValue(record.value(), MovieEntity.class))));

        if (updateResponse.getStatus() != 0) {
            LOGGER.error("Error while trying to add the documents. Response status: {}", updateResponse.getStatus());
            throw new RuntimeException("Error while trying to add the documents");
        }

    }


}
