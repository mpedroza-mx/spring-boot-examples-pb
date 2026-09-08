package org.learning.spring.spring_boot_solr_movies_search_service.service;



import org.apache.solr.client.solrj.SolrServerException;
import org.learning.spring.spring_boot_solr_movies_search_service.dto.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;

import java.util.List;
import java.util.Optional;



@Service
public class RecommendationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RecommendationService.class);
    private static final String MODEL = "llama3.2:3b";
    private final SearchService searchService;
    private final Optional<RestClient> optOllamaRestClient;
    private ObjectMapper objectMapper;

    public RecommendationService(SearchService searchService, Optional<RestClient> optOllamaRestClient, ObjectMapper objectMapper) {
        this.searchService = searchService;
        this.optOllamaRestClient = optOllamaRestClient;
        this.objectMapper = objectMapper;
    }

    public RecommendationResponseDto getRecommendedMovies(RecommendationRequestDto recommendationRequestDto) throws SolrServerException, IOException {
        SemanticSearchRequestDto semanticSearchRequestDto = SemanticSearchRequestDto.builder()
                .semanticQuery(recommendationRequestDto.getQuery())
                .build();

        SearchResponseDto searchResponseDto = searchService.search(semanticSearchRequestDto);
        PromptRequest promptRequest = PromptRequest.builder()
                .model(MODEL)
                .messages(List.of(Message.builder()
                        .role("system")
                        .content("You are a movies recommendation assistant. Use only the movies provided as context to make the recommendations")
                        .build(), Message.builder()
                        .role("user")
                        .content(buildMessageContent(searchResponseDto,semanticSearchRequestDto.getSemanticQuery())).build()))
                .stream(false)
                .build();

        LOGGER.debug ("REQUEST: {}" , objectMapper.writeValueAsString(promptRequest));

        PromptResponse promptResponse = optOllamaRestClient.get()
                .post()
                .uri("api/chat")
                .contentType(MediaType.APPLICATION_JSON)
                .body(promptRequest)
                .retrieve()
                .body(PromptResponse.class);


        LOGGER.debug ("RESPONSE: {}" , objectMapper.writeValueAsString(promptResponse));

        RecommendationResponseDto recommendationResponseDto = objectMapper.readValue( promptResponse.getMessage().getContent()
                .replace("```json","")
                .replace("```",""),RecommendationResponseDto.class);
        recommendationResponseDto.setQuery(recommendationRequestDto.getQuery());
        return recommendationResponseDto;

    }

    private String buildMessageContent(SearchResponseDto searchResponseDto, String query ){
        StringBuffer userContent = new StringBuffer("");
        userContent.append("Relevant Movies List Found: ");
        userContent.append("\\n");
        userContent.append("[");
        searchResponseDto.getMovies().stream().forEach(movie -> {
            String plot = "";
            if (movie.getPlot() != null){
                plot = movie.getPlot();
            }

            if (plot.isEmpty() && movie.getFullPlot() != null){
                plot = movie.getFullPlot();
            }
            userContent.append("{");

            userContent.append("\"");
            userContent.append("title");
            userContent.append("\": \"");
            userContent.append(movie.getTitle());
            userContent.append("\",");

            userContent.append("\"");
            userContent.append("year");
            userContent.append("\": \"");
            userContent.append(movie.getYear());
            userContent.append("\",");


            userContent.append("\"");
            userContent.append("plot");
            userContent.append("\": \"");
            userContent.append(plot);
            userContent.append("\",");

            userContent.append("\"");
            userContent.append("genres");
            userContent.append("\": \"");
            userContent.append(movie.getGenres().toString());
            userContent.append("\",");

            userContent.append("},");
        });
        userContent.append("]");


        userContent.append("Question from User: " + query);
        userContent.append("\\n");
        userContent.append("Return exactly 5 recommendations. Use this JSON format for your response\\n");
        userContent.append("{\"moviesRecommendations\": [{\"movieTitle\": \"The Day the Earth Stood Still\", \"reason\": \"An alien lands and tells the people of Earth that they must live peacefully or be destroyed\"}]}\\n");
        userContent.append("The reason must be short sentence.");
        return userContent.toString();
    }

}
