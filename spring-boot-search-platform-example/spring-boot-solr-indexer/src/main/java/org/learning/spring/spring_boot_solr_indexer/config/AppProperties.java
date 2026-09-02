package org.learning.spring.spring_boot_solr_indexer.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "app")
@Data
public class AppProperties {
    private String solrUrl;
    private String kafkaTopic;
    private String ollamaUrl;
    private boolean semanticSearchEnabled;

}
