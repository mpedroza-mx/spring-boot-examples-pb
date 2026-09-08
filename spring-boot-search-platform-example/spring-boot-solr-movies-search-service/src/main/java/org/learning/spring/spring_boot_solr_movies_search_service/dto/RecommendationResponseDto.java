package org.learning.spring.spring_boot_solr_movies_search_service.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecommendationResponseDto {
    private String query;
    private List<MovieRecommendation> moviesRecommendations;



    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    public static class MovieRecommendation {
        private String movieTitle;
        private String reason;
    }
}
