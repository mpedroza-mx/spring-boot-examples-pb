package org.learning.spring.spring_boot_solr_movies_search_service.service;

import org.apache.solr.client.solrj.SolrResponse;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrDocumentList;
import org.learning.spring.spring_boot_solr_movies_search_service.dto.*;
import org.learning.spring.spring_boot_solr_movies_search_service.mapper.MovieMapper;
import org.learning.spring.spring_boot_solr_movies_search_service.repository.SolrMoviesRepository;
import org.learning.spring.spring_boot_solr_movies_search_service.util.SolrQueryBuilder;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SearchService {

    private final SolrMoviesRepository solrMoviesRepository;
    private final MovieMapper movieMapper;
    private final SolrQueryBuilder solrQueryBuilder;
    private final RestClient ollamaRestClient;
    private static final String MODEL = "nomic-embed-text";

    public SearchService(SolrMoviesRepository solrMoviesRepository, MovieMapper movieMapper, SolrQueryBuilder solrQueryBuilder, RestClient ollamaRestClient) {
        this.solrMoviesRepository = solrMoviesRepository;
        this.movieMapper = movieMapper;
        this.solrQueryBuilder = solrQueryBuilder;
        this.ollamaRestClient = ollamaRestClient;
    }

    public SearchResponseDto search(SearchKeyWordRequestDto searchKeyWordRequestDto) throws SolrServerException, IOException {
        String query = solrQueryBuilder.buildQueryString(searchKeyWordRequestDto);
        String[] filterQuery = null;
        if (Optional.ofNullable(searchKeyWordRequestDto.getFilters()).isPresent() && !searchKeyWordRequestDto.getFilters().isEmpty()) {
            filterQuery = (solrQueryBuilder.buildFiltersString(searchKeyWordRequestDto));
        }
        return processSolrResponse(solrMoviesRepository.query(query, filterQuery));

    }

    public SearchResponseDto search(SemanticSearchRequestDto semanticSearchRequestDto) throws SolrServerException, IOException {
        return processSolrResponse(solrMoviesRepository.query(ollamaRestClient
                .post()
                .uri("api/embed")
                .contentType(MediaType.APPLICATION_JSON)
                .body(EmbeddingRequest.builder()
                        .input(semanticSearchRequestDto.getSemanticQuery())
                        .model(MODEL)
                        .build())
                .retrieve()
                .body(EmbeddingResponse.class)
                .getEmbeddings()
                .get(0)));

    }

    private SearchResponseDto processSolrResponse(SolrResponse solrResponse) {
        SolrDocumentList solrDocumentList = (SolrDocumentList) solrResponse.getResponse().get("response");
        List<Movie> movies = new ArrayList<>();
        for (int index = 0; index < solrDocumentList.size(); index++) {
            movies.add(movieMapper.toMovie(solrDocumentList.get(index)));
        }
        SearchResponseDto searchResponseDto = SearchResponseDto.builder()
                .movies(movies)
                .numOfMoviesFound(movies.size())
                .build();
        return searchResponseDto;
    }
}
