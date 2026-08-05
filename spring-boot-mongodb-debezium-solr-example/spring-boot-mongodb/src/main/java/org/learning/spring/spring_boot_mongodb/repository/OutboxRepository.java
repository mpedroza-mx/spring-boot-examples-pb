package org.learning.spring.spring_boot_mongodb.repository;

import org.bson.types.ObjectId;
import org.learning.spring.spring_boot_mongodb.entity.OutboxEvent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutboxRepository extends MongoRepository<OutboxEvent, ObjectId> {
}
