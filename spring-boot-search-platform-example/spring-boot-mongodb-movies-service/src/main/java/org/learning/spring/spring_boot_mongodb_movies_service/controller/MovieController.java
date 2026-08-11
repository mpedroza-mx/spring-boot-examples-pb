package org.learning.spring.spring_boot_mongodb_movies_service.controller;

import org.learning.spring.spring_boot_mongodb_movies_service.entity.Movie;
import org.learning.spring.spring_boot_mongodb_movies_service.service.MovieService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;


    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    public Movie createMovie(@RequestBody Movie newMovie){

        return  movieService.createMovie(newMovie);
    }
}
