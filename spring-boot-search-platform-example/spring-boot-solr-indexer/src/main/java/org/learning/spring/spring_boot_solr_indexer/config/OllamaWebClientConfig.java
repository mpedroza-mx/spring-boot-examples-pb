package org.learning.spring.spring_boot_solr_indexer.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@ConditionalOnProperty(name = "app.semantic_search_enabled", havingValue = "true")
public class OllamaWebClientConfig {

    private final AppProperties appProperties;

    public OllamaWebClientConfig(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    @Bean
    public WebClient ollamaRestClient (){
        return WebClient.builder()
                .codecs(codecs->codecs.defaultCodecs().maxInMemorySize(10*1024*1024))
                .baseUrl(appProperties.getOllamaUrl())
                .build();
    }

}
