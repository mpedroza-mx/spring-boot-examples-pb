package org.learning.spring.spring_boot_solr_movies_search_service.dto;

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

}
