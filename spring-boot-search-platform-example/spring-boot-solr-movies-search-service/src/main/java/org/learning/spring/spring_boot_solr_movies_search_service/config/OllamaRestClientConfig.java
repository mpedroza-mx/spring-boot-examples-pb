package org.learning.spring.spring_boot_solr_movies_search_service.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

import java.time.Duration;

@Configuration
@ConditionalOnProperty(name = "app.semantic_search_enabled", havingValue = "true")
public class OllamaRestClientConfig {

    private final AppProperties appProperties;

    public OllamaRestClientConfig(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    @Bean
    public RestClient ollamaRestClient (){
        var factory = new JdkClientHttpRequestFactory();
        factory.setReadTimeout(Duration.ofMinutes(1));
        return RestClient.builder()
                .baseUrl(appProperties.getOllamaUrl())
                .requestFactory(factory)
                .build();
    }

}
