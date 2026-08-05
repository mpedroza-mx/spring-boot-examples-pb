package org.learning.spring.spring_boot_solr_search_service.dto;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchRequestDto {
    private String query;
    private List<Filterable> filters;
}
