package org.learning.spring.spring_boot_solr_movies_search_service.util;

import org.learning.spring.spring_boot_solr_movies_search_service.config.AppProperties;
import org.learning.spring.spring_boot_solr_movies_search_service.dto.Filterable;
import org.learning.spring.spring_boot_solr_movies_search_service.dto.SearchRequestDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class SolrQueryBuilder {
    private final AppProperties appProperties;
    private final SolrFilterOperatorResolver solrFilterOperatorResolver;

    public SolrQueryBuilder(AppProperties appProperties, SolrFilterOperatorResolver solrFilterOperatorResolver) {
        this.appProperties = appProperties;
        this.solrFilterOperatorResolver = solrFilterOperatorResolver;
    }

    public String buildQueryString(SearchRequestDto searchRequestDto) {
        List<String> searchableFields = appProperties.getSolrSearchableFields();
        StringBuilder buildQueryStringField = new StringBuilder();
        AtomicInteger fieldsCounter = new AtomicInteger(0);
        searchableFields.forEach(field -> {
            buildQueryStringField.append(field);
            buildQueryStringField.append(":");
            buildQueryStringField.append(searchRequestDto.getQuery());
            if (searchableFields.size() - fieldsCounter.get() > 1) {
                buildQueryStringField.append(" OR ");
            }
            fieldsCounter.getAndIncrement();
        });

        return buildQueryStringField.toString();
    }

    public String[] buildFiltersString(SearchRequestDto searchRequestDto) {
        List<String> filterableFields = appProperties.getSolrFilterableFields();
        String[] fq = new String[searchRequestDto.getFilters().size()];

        AtomicInteger filterCounter = new AtomicInteger(0);
        for (Filterable filter : searchRequestDto.getFilters()) {
            if (!filterableFields.contains(filter.getField())) {
                throw new RuntimeException("Invalid field provided");
            }

            fq[filterCounter.get()] = (solrFilterOperatorResolver.resolveOperator(filter.getOperator())
                    .apply(filter));
            filterCounter.getAndIncrement();
        }

        return fq;
    }


}
