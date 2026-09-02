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
public class EmbeddingResponse {
    private String model;
    private List<List<Float>> embeddings;
    private long total_duration;
    private long load_duration;
    private int prompt_eval_count;
}
