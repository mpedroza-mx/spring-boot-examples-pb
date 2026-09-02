package org.learning.spring.spring_boot_solr_movies_search_service.repository;

import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.jetty.HttpJettySolrClient;
import org.apache.solr.client.solrj.request.QueryRequest;
import org.apache.solr.client.solrj.request.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;


import java.util.List;
import java.util.Optional;



@Component
public class SolrMoviesRepository {
    private final HttpJettySolrClient httpJettySolrClient;

    public SolrMoviesRepository(HttpJettySolrClient httpJettySolrClient) {
        this.httpJettySolrClient = httpJettySolrClient;
    }

    public QueryResponse query(String query, String[] filterQuery) throws SolrServerException, IOException {
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery(query);
        if (Optional.ofNullable(filterQuery).isPresent() && filterQuery.length > 0) {
            solrQuery.setFilterQueries(filterQuery);
        }
        return httpJettySolrClient.query(solrQuery);
    }

    public QueryResponse query(List<Float> embeddings) throws SolrServerException, IOException {
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery("{!knn f=vectors topK=10}" +embeddings);
        QueryRequest queryRequest = new QueryRequest(solrQuery);
        queryRequest.setMethod(SolrRequest.METHOD.POST);


        return queryRequest.process(httpJettySolrClient);
    }

}
