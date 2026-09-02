package org.learning.spring.spring_boot_solr_movies_search_service.util;

import org.learning.spring.spring_boot_solr_movies_search_service.config.AppProperties;
import org.learning.spring.spring_boot_solr_movies_search_service.dto.Filterable;
import org.learning.spring.spring_boot_solr_movies_search_service.dto.SearchKeyWordRequestDto;
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

    public String buildQueryString(SearchKeyWordRequestDto searchKeyWordRequestDto) {
        List<String> searchableFields = appProperties.getSolrSearchableFields();
        StringBuilder buildQueryStringField = new StringBuilder();
        AtomicInteger fieldsCounter = new AtomicInteger(0);
        searchableFields.forEach(field -> {
            buildQueryStringField.append(field);
            buildQueryStringField.append(":");
            buildQueryStringField.append(searchKeyWordRequestDto.getQuery());
            if (searchableFields.size() - fieldsCounter.get() > 1) {
                buildQueryStringField.append(" OR ");
            }
            fieldsCounter.getAndIncrement();
        });

        return buildQueryStringField.toString();
    }

    public String[] buildFiltersString(SearchKeyWordRequestDto searchKeyWordRequestDto) {
        List<String> filterableFields = appProperties.getSolrFilterableFields();
        String[] fq = new String[searchKeyWordRequestDto.getFilters().size()];

        AtomicInteger filterCounter = new AtomicInteger(0);
        for (Filterable filter : searchKeyWordRequestDto.getFilters()) {
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
