package org.learning.spring.spring_boot_solr_movies_search_service.util;

import org.learning.spring.spring_boot_solr_movies_search_service.dto.EqualsFilter;
import org.learning.spring.spring_boot_solr_movies_search_service.dto.Filterable;
import org.learning.spring.spring_boot_solr_movies_search_service.dto.InFilter;
import org.learning.spring.spring_boot_solr_movies_search_service.dto.RangeFilter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

@Component
public class SolrFilterOperatorResolver {
    private final Map<String, Function<Filterable,String>> mapFilterableField = new HashMap<>();

    public SolrFilterOperatorResolver() {
        mapFilterableField.put("EQUALS",(filterable)->{
            EqualsFilter equalsFilter = (EqualsFilter) filterable;
            StringBuilder filterString  = new StringBuilder();
            filterString.append(equalsFilter.getField());
            filterString.append(":");
            filterString.append(equalsFilter.getValue());
            return filterString.toString();

        });


        mapFilterableField.put("RANGE",(filterable)->{
            RangeFilter rangeFilter = (RangeFilter) filterable;
            StringBuilder filterString  = new StringBuilder();
            filterString.append(rangeFilter.getField());
            filterString.append(":[");
            filterString.append(rangeFilter.getFrom());
            filterString.append(" TO ");
            filterString.append(rangeFilter.getTo());
            filterString.append("]");
            return filterString.toString();
        });

        mapFilterableField.put("IN",(filterable)->{
            InFilter inFilter = (InFilter) filterable;
            StringBuilder filterString  = new StringBuilder();
            filterString.append(inFilter.getField());
            filterString.append(":(");
            AtomicInteger valuesCounter = new AtomicInteger(0);
            for (String value: inFilter.getValues()){
                filterString.append(value);
                if (inFilter.getValues().size() - valuesCounter.get() > 1 ) {
                    filterString.append(" OR ");
                }
                valuesCounter.getAndIncrement();
            }
            filterString.append(")");

            return filterString.toString();
        });
    }

    public Function<Filterable,String> resolveOperator(String operator){
        return mapFilterableField.get(operator);
    }
}
