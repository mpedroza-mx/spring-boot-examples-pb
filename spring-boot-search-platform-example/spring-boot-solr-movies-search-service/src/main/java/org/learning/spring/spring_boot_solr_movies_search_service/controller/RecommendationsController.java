package org.learning.spring.spring_boot_solr_movies_search_service.controller;


import org.apache.solr.client.solrj.SolrServerException;
import org.learning.spring.spring_boot_solr_movies_search_service.dto.RecommendationRequestDto;
import org.learning.spring.spring_boot_solr_movies_search_service.dto.RecommendationResponseDto;

import org.learning.spring.spring_boot_solr_movies_search_service.service.RecommendationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
@RequestMapping("/api/movies/recommedations")
public class RecommendationsController {

    private RecommendationService recommendationService;

    public RecommendationsController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @PostMapping
    public RecommendationResponseDto moviesRecommendations(@RequestBody RecommendationRequestDto recommendationRequestDto) throws SolrServerException, IOException {
        return  recommendationService.getRecommendedMovies(recommendationRequestDto);
    }
}
