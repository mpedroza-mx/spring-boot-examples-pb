package org.learning.spring.spring_boot_mongodb.controller;

import org.learning.spring.spring_boot_mongodb.entity.Movie;
import org.learning.spring.spring_boot_mongodb.service.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;


    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<Movie> getAllMovies(){
        return movieService.getMovies();
    }


    @PostMapping
    public Movie createMovie(@RequestBody Movie newMovie){

        return  movieService.createMovie(newMovie);
    }
}
