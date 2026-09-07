package org.learning.spring.spring_boot_solr_movies_search_service.service;


import org.apache.solr.client.solrj.SolrServerException;
import org.learning.spring.spring_boot_solr_movies_search_service.dto.*;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.io.IOException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class RecommendationService {

    private SearchService searchService;
    private final Optional<RestClient> optOllamaRestClient;
    private static final String MODEL = "qwen3:4b";

    public RecommendationService(Optional<RestClient> optOllamaRestClient, SearchService searchService) {
        this.optOllamaRestClient = optOllamaRestClient;
        this.searchService = searchService;
    }

    public RecommendationResponseDto getRecommendedMovies(RecommendationRequestDto recommendationRequestDto) throws SolrServerException, IOException {
        SemanticSearchRequestDto semanticSearchRequestDto = SemanticSearchRequestDto.builder()
                .semanticQuery(recommendationRequestDto.getSematicQuery())
                .build();
        SearchResponseDto searchResponseDto = searchService.search(semanticSearchRequestDto);

        String moviesTitles = String.join("* ", searchResponseDto.getMovies().stream().map(Movie::getTitle).collect(Collectors.toList()));
        StringBuffer userContent = new StringBuffer("");
        userContent.append("Relevant Movies List Found: " + moviesTitles);
        userContent.append("\\n");
        userContent.append("Question from User: " + recommendationRequestDto.getSematicQuery());
        userContent.append("\\n");
        userContent.append("Response Format: I need that in your response you include 2 fields: the movie title and the reason why you recommend it");
        PromptRequest promptRequest = PromptRequest.builder()
                .model(MODEL)
                .messages(List.of(Message.builder()
                        .role("system")
                        .content("You are a movies recommendation assistant. Use only the movies provided as context to make the recommendations")
                        .build(), Message.builder()
                        .role("user")
                        .content(userContent.toString()).build()))
                .stream(false)
                .build();

        PromptResponse promptResponse = optOllamaRestClient.get()
                .post()
                .uri("api/chat")
                .contentType(MediaType.APPLICATION_JSON)
                .body(promptRequest)
                .retrieve()
                .body(PromptResponse.class);


        return null;

    }

}
