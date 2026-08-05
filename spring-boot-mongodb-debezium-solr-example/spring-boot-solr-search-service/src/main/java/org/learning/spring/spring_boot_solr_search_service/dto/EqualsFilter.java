package org.learning.spring.spring_boot_solr_search_service.dto;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class EqualsFilter implements Filterable {
    private String field;
    private String operator;
    private String value;
}
