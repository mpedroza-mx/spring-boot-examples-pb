package org.learning.spring.spring_boot_mongodb.repository;

import org.bson.types.ObjectId;
import org.learning.spring.spring_boot_mongodb.entity.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends MongoRepository<Movie, ObjectId> {
}
