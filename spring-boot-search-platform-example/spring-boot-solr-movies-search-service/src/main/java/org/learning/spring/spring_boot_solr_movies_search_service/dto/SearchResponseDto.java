package org.learning.spring.spring_boot_solr_movies_search_service.dto;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchResponseDto {
    private List<Movie> movies;
    private int numOfMoviesFound;
}
