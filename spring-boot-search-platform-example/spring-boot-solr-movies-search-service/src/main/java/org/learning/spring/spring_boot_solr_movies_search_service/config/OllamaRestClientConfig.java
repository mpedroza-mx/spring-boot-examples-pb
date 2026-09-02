package org.learning.spring.spring_boot_solr_movies_search_service.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
@ConditionalOnProperty(name = "app.semantic_search_enabled", havingValue = "true")
public class OllamaRestClientConfig {

    private final AppProperties appProperties;

    public OllamaRestClientConfig(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    @Bean
    public RestClient ollamaRestClient (){
        return RestClient.builder()
                .baseUrl(appProperties.getOllamaUrl())
                .build();
    }

}
