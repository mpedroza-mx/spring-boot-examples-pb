package org.learning.spring.spring_boot_solr_movies_search_service.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "app")
@Getter
@Setter
public class AppProperties {
    private List<String> solrSearchableFields;
    private List<String> solrFilterableFields;
    private String solrUrl;
    private String ollamaUrl;
    private boolean semanticSearchEnabled;

}
