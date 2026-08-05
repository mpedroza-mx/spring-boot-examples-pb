package org.learning.spring.spring_boot_mongodb_movies_service.repository;

import org.bson.types.ObjectId;
import org.learning.spring.spring_boot_mongodb_movies_service.entity.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends MongoRepository<Movie, ObjectId> {
}
