package org.learning.spring.spring_boot_solr_movies_search_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmbeddingRequest {
    private String input;
    private String model;
}
