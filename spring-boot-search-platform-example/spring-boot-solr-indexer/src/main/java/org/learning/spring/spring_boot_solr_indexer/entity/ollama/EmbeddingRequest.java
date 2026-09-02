package org.learning.spring.spring_boot_solr_indexer.entity.ollama;

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
    private List<String> input;
    private String model;
}
