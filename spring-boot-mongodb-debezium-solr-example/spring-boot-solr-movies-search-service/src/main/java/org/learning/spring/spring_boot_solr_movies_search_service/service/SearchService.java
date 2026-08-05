package org.learning.spring.spring_boot_solr_movies_search_service.service;

import org.apache.solr.client.solrj.SolrResponse;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrDocumentList;
import org.learning.spring.spring_boot_solr_movies_search_service.dto.Movie;
import org.learning.spring.spring_boot_solr_movies_search_service.mapper.MovieMapper;
import org.learning.spring.spring_boot_solr_movies_search_service.repository.SolrMoviesRepository;
import org.learning.spring.spring_boot_solr_movies_search_service.dto.SearchRequestDto;
import org.learning.spring.spring_boot_solr_movies_search_service.dto.SearchResponseDto;
import org.learning.spring.spring_boot_solr_movies_search_service.util.SolrQueryBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SearchService {

    private final SolrMoviesRepository solrMoviesRepository;
    private final MovieMapper movieMapper;
    private final SolrQueryBuilder solrQueryBuilder;

    public SearchService(SolrMoviesRepository solrMoviesRepository, MovieMapper movieMapper, SolrQueryBuilder solrQueryBuilder) {
        this.solrMoviesRepository = solrMoviesRepository;
        this.movieMapper = movieMapper;
        this.solrQueryBuilder = solrQueryBuilder;
    }

    public SearchResponseDto search(SearchRequestDto searchRequestDto) throws SolrServerException, IOException {
        String query = solrQueryBuilder.buildQueryString(searchRequestDto);
        String[] filterQuery = null;
        if (Optional.ofNullable(searchRequestDto.getFilters()).isPresent() && !searchRequestDto.getFilters().isEmpty()) {
           filterQuery = (solrQueryBuilder.buildFiltersString(searchRequestDto));
        }
        SolrResponse solrResponse = solrMoviesRepository.query(query,filterQuery);
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
