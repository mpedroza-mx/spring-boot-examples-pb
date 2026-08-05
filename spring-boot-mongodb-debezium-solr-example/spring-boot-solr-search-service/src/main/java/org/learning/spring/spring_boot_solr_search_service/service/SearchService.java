package org.learning.spring.spring_boot_solr_search_service.service;

import org.apache.solr.client.solrj.SolrResponse;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrDocumentList;
import org.learning.spring.spring_boot_solr_search_service.dto.Movie;
import org.learning.spring.spring_boot_solr_search_service.mapper.MovieMapper;
import org.learning.spring.spring_boot_solr_search_service.repository.SolrMoviesRepository;
import org.learning.spring.spring_boot_solr_search_service.dto.SearchRequestDto;
import org.learning.spring.spring_boot_solr_search_service.dto.SearchResponseDto;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {

    private final SolrMoviesRepository solrMoviesRepository;
    private final MovieMapper movieMapper;

    public SearchService(SolrMoviesRepository solrMoviesRepository, MovieMapper movieMapper) {
        this.solrMoviesRepository = solrMoviesRepository;
        this.movieMapper = movieMapper;
    }

    public SearchResponseDto search(SearchRequestDto searchRequestDto) throws SolrServerException, IOException {
        SolrResponse solrResponse = solrMoviesRepository.query(searchRequestDto);
        SolrDocumentList solrDocumentList =  (SolrDocumentList)solrResponse.getResponse().get("response");
        List<Movie> movies = new ArrayList<>();
        for (int index=0; index < solrDocumentList.size(); index++){
            movies.add(movieMapper.toMovie(solrDocumentList.get(index)));
        }
        SearchResponseDto searchResponseDto = SearchResponseDto.builder()
                .movies(movies)
                .numOfMoviesFound(movies.size())
                .build();
        return searchResponseDto;

    }
}
