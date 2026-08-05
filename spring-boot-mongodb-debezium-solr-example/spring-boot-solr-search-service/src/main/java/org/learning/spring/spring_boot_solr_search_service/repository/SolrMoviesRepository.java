package org.learning.spring.spring_boot_solr_search_service.repository;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.jetty.HttpJettySolrClient;
import org.apache.solr.client.solrj.request.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.learning.spring.spring_boot_solr_search_service.config.AppProperties;
import org.learning.spring.spring_boot_solr_search_service.dto.SearchRequestDto;
import org.learning.spring.spring_boot_solr_search_service.util.SolrQueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class SolrMoviesRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(SolrMoviesRepository.class);
    private final HttpJettySolrClient httpJettySolrClient;
    private final SolrQueryBuilder solrQueryBuilder;

    public SolrMoviesRepository(HttpJettySolrClient httpJettySolrClient, SolrQueryBuilder solrQueryBuilder) {
        this.httpJettySolrClient = httpJettySolrClient;
        this.solrQueryBuilder = solrQueryBuilder;
    }

    public QueryResponse query(SearchRequestDto searchRequestDto) throws SolrServerException, IOException {
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery(solrQueryBuilder.buildQueryString(searchRequestDto));
        if (Optional.ofNullable(searchRequestDto.getFilters()).isPresent() && !searchRequestDto.getFilters().isEmpty()) {
            solrQuery.setFilterQueries(solrQueryBuilder.buildFiltersString(searchRequestDto));
        }
        return httpJettySolrClient.query(solrQuery);
    }

}
