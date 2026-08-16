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
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/api/movies/search")
public class SearchController {

   private final SearchService searchService;

   private static final AtomicInteger COUNTER = new AtomicInteger(0);

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @PostMapping
    public SearchResponseDto searchMovies(@RequestBody SearchRequestDto searchRequestDto) throws SolrServerException, IOException, InterruptedException {

        //Simulate delay to activate Prometheus Alert
//        if (COUNTER.getAndIncrement() % 2 == 0){
//            Thread.sleep(10000);
//        }

        return searchService.search(searchRequestDto);
    }
}
