package org.learning.spring.spring_boot_solr_search_service;

import org.learning.spring.spring_boot_solr_search_service.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class SpringBootSolrSearchServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSolrSearchServiceApplication.class, args);
	}

}
