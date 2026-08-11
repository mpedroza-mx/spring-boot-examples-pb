package org.learning.spring.spring_boot_solr_indexer;


import org.learning.spring.spring_boot_solr_indexer.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class SpringBootSolrIndexerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSolrIndexerApplication.class, args);
    }



}
