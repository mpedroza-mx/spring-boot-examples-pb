package org.learning.spring.spring_boot_solr_indexer.listener;

import jakarta.annotation.PostConstruct;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.solr.client.solrj.RemoteSolrException;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.learning.spring.spring_boot_solr_indexer.entity.solr.MovieSolrEntity;
import org.learning.spring.spring_boot_solr_indexer.mapper.MovieMapper;
import org.learning.spring.spring_boot_solr_indexer.repository.solr.SolrMoviesRepository;
import org.learning.spring.spring_boot_solr_indexer.entity.mongo.MovieEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

@Component
public class MessageListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageListener.class);
    private final SolrMoviesRepository solrMoviesRepository;
    private final MovieMapper movieMapper;
    private final ObjectMapper objectMapper;
    private final AtomicInteger counter = new AtomicInteger(1);

    private final Map<String, Function<ConsumerRecord<String,String>,MovieSolrEntity>> eventResolver = new HashMap<>();

    public MessageListener(SolrMoviesRepository solrMoviesRepository, MovieMapper movieMapper, ObjectMapper objectMapper) {
        this.solrMoviesRepository = solrMoviesRepository;
        this.movieMapper = movieMapper;
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    void init(){
        eventResolver.put("MovieCreated",(record->{
            MovieSolrEntity movieSolrEntity = movieMapper.toMovieSolrEntity(objectMapper.readValue(record.value(), MovieEntity.class));
            movieSolrEntity.setVersion(-1L);
            return movieSolrEntity;
        }));
    }

    @KafkaListener(id = "spring-boot-solr-indexer",
            topics = "${app.kafka-topic}")
    public void consumeMessage(ConsumerRecord<String, String> record) throws SolrServerException, IOException {
       LOGGER.info("Record message: {}", record.toString());
       MovieSolrEntity movieSolrEntity = eventResolver.get(new String (record.headers().lastHeader("eventType").value(), StandardCharsets.UTF_8))
               .apply(record);
        try {
            UpdateResponse updateResponse = solrMoviesRepository.sendDocuments(List.of(movieSolrEntity));
            if (updateResponse.getStatus() != 0) {
                LOGGER.error("Error while trying to add the documents. Response status: {}", updateResponse.getStatus());
                throw new RuntimeException("Error while trying to add the documents");
            }
        }catch (RemoteSolrException rse){
            LOGGER.warn("We already have this movie in solr: {}",movieSolrEntity.getTitle());
        }

        if (counter.getAndIncrement() % 2 == 0) {
            throw new RuntimeException("Force to message redelivery");
        }



    }


}
