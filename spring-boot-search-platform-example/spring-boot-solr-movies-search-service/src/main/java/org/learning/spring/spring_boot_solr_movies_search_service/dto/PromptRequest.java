package org.learning.spring.spring_boot_solr_movies_search_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PromptRequest {
    private String model;
    private List<Message> messages;
    private boolean stream;
    private String format;
    private Options options;


    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    public static class Options {
        @JsonProperty("num_predict")
        private int numPredict;

    }

}
