package org.learning.spring.spring_boot_solr_indexer.config;

import org.apache.solr.client.solrj.jetty.HttpJettySolrClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tools.jackson.databind.ObjectMapper;


import java.util.concurrent.TimeUnit;

@Configuration
public class SolrConfig {

    private final AppProperties appProperties;

    public SolrConfig(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public HttpJettySolrClient httpJettySolrClient() {
        return new HttpJettySolrClient.Builder(appProperties.getSolrUrl())
                .withConnectionTimeout(10000, TimeUnit.MILLISECONDS)
                .withIdleTimeout(60000, TimeUnit.MILLISECONDS)
                .build();
    }
}
