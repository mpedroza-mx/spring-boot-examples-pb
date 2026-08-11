package org.learning.spring.spring_boot_solr_indexer.repository.mongo;

import org.bson.types.ObjectId;
import org.learning.spring.spring_boot_solr_indexer.entity.mongo.MovieEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends MongoRepository<MovieEntity, ObjectId> {
}
