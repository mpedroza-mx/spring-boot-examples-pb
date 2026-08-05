package org.learning.spring.spring_boot_solr_movies_search_service.dto;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class InFilter implements Filterable{
    private String field;
    private String operator;
    private List<String> values;
}
