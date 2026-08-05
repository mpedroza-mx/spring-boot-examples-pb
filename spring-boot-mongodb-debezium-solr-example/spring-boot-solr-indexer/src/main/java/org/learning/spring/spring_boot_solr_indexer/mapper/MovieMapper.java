package org.learning.spring.spring_boot_solr_indexer.mapper;

import org.bson.types.ObjectId;
import org.learning.spring.spring_boot_solr_indexer.entity.mongo.MovieEntity;
import org.learning.spring.spring_boot_solr_indexer.entity.solr.MovieSolrEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;


@Mapper(componentModel = "spring")
public interface MovieMapper {


    @Mapping(source = "id", qualifiedByName = "convertObjectIdToString", target = "id")
    MovieSolrEntity toMovieSolrEntity(MovieEntity movieEntity);


    @Named("convertObjectIdToString")
    default String convertObjectIdToString(ObjectId objectId){
        if (objectId == null){
            return null;
        }
        return objectId.toHexString();
    }
}
