package org.learning.spring.spring_boot_solr_movies_search_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SemanticSearchRequestDto implements SearchRequestDto{
    private String semanticQuery;
}
