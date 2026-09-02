package org.learning.spring.spring_boot_solr_movies_search_service.controller;


import org.apache.solr.client.solrj.SolrServerException;

import org.learning.spring.spring_boot_solr_movies_search_service.config.AppProperties;
import org.learning.spring.spring_boot_solr_movies_search_service.dto.SemanticSearchRequestDto;
import org.learning.spring.spring_boot_solr_movies_search_service.service.SearchService;
import org.learning.spring.spring_boot_solr_movies_search_service.dto.SearchKeyWordRequestDto;
import org.learning.spring.spring_boot_solr_movies_search_service.dto.SearchResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/api/movies")
public class SearchController {

   private final SearchService searchService;
   private final AppProperties appProperties;

   private static final AtomicInteger COUNTER = new AtomicInteger(0);

    public SearchController(SearchService searchService, AppProperties appProperties) {
        this.searchService = searchService;
        this.appProperties = appProperties;
    }

    @PostMapping("/search")
    public SearchResponseDto searchMovies(@RequestBody SearchKeyWordRequestDto searchKeyWordRequestDto) throws SolrServerException, IOException{

        //Simulate delay to activate Prometheus Alert
//        if (COUNTER.getAndIncrement() % 2 == 0){
//            Thread.sleep(10000);
//        }

        return searchService.search(searchKeyWordRequestDto);
    }


    @PostMapping("/semantic-search")
    public SearchResponseDto searchMoviesUsingVector(@RequestBody SemanticSearchRequestDto semanticSearchRequestDto) throws SolrServerException, IOException {
        if(appProperties.isSemanticSearchEnabled()) {
            return searchService.search(semanticSearchRequestDto);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Semantic Search Disabled");
    }
}
