package org.learning.spring.spring_boot_mongodb_movies_service;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutboxRepository extends MongoRepository<OutboxEvent, ObjectId> {
}
