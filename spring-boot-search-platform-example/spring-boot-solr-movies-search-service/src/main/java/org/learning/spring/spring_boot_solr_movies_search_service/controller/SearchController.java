package org.learning.spring.spring_boot_solr_movies_search_service.controller;


import org.apache.solr.client.solrj.SolrServerException;

import org.learning.spring.spring_boot_solr_movies_search_service.service.SearchService;
import org.learning.spring.spring_boot_solr_movies_search_service.dto.SearchRequestDto;
import org.learning.spring.spring_boot_solr_movies_search_service.dto.SearchResponseDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/movies/search")
public class SearchController {

   private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @PostMapping
    public SearchResponseDto searchMovies(@RequestBody SearchRequestDto searchRequestDto) throws SolrServerException, IOException {
        return searchService.search(searchRequestDto);
    }
}
