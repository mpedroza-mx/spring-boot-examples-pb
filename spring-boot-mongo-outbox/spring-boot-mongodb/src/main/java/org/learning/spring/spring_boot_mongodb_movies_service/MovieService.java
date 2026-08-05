package org.learning.spring.spring_boot_mongodb_movies_service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    private final OutboxRepository outboxRepository;


    public MovieService(MovieRepository movieRepository, OutboxRepository outboxRepository) {
        this.movieRepository = movieRepository;
        this.outboxRepository = outboxRepository;
    }

    public List<Movie> getMovies(){

        return movieRepository.findAll();
    }


    @Transactional
    public Movie createMovie(Movie movie){
        Movie newMovie = movieRepository.save(movie);
        OutboxEvent outboxEvent = OutboxEvent.builder()
                .aggregateId(movie.getId())
                .aggregateType(movie.getType().toUpperCase())
                .type("MovieCreated")
                .payload(newMovie)
                .timestamp(LocalDateTime.now()
                        .atZone(ZoneId.of("UTC"))
                        .toInstant()
                        .toEpochMilli())
                .build();
        outboxRepository.save(outboxEvent);

        return newMovie ;
    }
}
