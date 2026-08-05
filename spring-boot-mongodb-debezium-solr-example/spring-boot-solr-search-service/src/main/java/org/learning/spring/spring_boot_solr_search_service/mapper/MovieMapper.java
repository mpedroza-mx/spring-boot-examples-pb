package org.learning.spring.spring_boot_solr_search_service.mapper;

import org.apache.solr.common.SolrDocument;
import org.learning.spring.spring_boot_solr_search_service.dto.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface MovieMapper {

    @Mapping(expression = "java((Integer)solrDocument.getFieldValue(\"awards.nominations\"))", target = "awards.nominations")
    @Mapping(expression = "java((String)solrDocument.getFieldValue(\"awards.text\"))", target = "awards.text")
    @Mapping(expression = "java((Integer)solrDocument.getFieldValue(\"awards.wins\"))", target = "awards.wins")
    @Mapping(expression = "java((java.util.ArrayList)solrDocument.getFieldValue(\"cast\"))", target = "cast")
    @Mapping(expression = "java((java.util.ArrayList)solrDocument.getFieldValue(\"countries\"))", target = "countries")
    @Mapping(expression = "java((java.util.ArrayList)solrDocument.getFieldValue(\"directors\"))", target = "directors")
    @Mapping(expression = "java((String)solrDocument.getFieldValue(\"fullPlot\"))", target = "fullPlot")
    @Mapping(expression = "java((java.util.ArrayList)solrDocument.getFieldValue(\"genres\"))", target = "genres")
    @Mapping(expression = "java((String)solrDocument.getFieldValue(\"id\"))", target = "id")
    @Mapping(expression = "java((Integer)solrDocument.getFieldValue(\"imdb.imdbId\"))", target = "imdb.imdbId")
    @Mapping(expression = "java((Double)solrDocument.getFieldValue(\"imdb.rating\"))", target = "imdb.rating")
    @Mapping(expression = "java((Integer)solrDocument.getFieldValue(\"imdb.votes\"))", target = "imdb.votes")
    @Mapping(expression = "java((java.util.ArrayList)solrDocument.getFieldValue(\"languages\"))", target = "languages")
    @Mapping(expression = "java((String)solrDocument.getFieldValue(\"lastUpdated\"))", target = "lastUpdated")
    @Mapping(expression = "java((String)solrDocument.getFieldValue(\"plot\"))", target = "plot")
    @Mapping(expression = "java((String)solrDocument.getFieldValue(\"poster\"))", target = "poster")
    @Mapping(expression = "java((String)solrDocument.getFieldValue(\"rated\"))", target = "rated")
    @Mapping(expression = "java((String)solrDocument.getFieldValue(\"released\"))", target = "released")
    @Mapping(expression = "java((Integer)solrDocument.getFieldValue(\"runtime\"))", target = "runtime")
    @Mapping(expression = "java((String)solrDocument.getFieldValue(\"title\"))", target = "title")
    @Mapping(expression = "java((Integer)solrDocument.getFieldValue(\"tomatoes.critic.meter\"))", target = "tomatoes.critic.meter")
    @Mapping(expression = "java((Integer)solrDocument.getFieldValue(\"tomatoes.critic.numReviews\"))", target = "tomatoes.critic.numReviews")
    @Mapping(expression = "java((Double)solrDocument.getFieldValue(\"tomatoes.critic.rating\"))", target = "tomatoes.critic.rating")
    @Mapping(expression = "java((Integer)solrDocument.getFieldValue(\"tomatoes.fresh\"))", target = "tomatoes.fresh")
    @Mapping(expression = "java((String)solrDocument.getFieldValue(\"tomatoes.lastUpdated\"))", target = "tomatoes.lastUpdated")
    @Mapping(expression = "java((Integer)solrDocument.getFieldValue(\"tomatoes.rotten\"))", target = "tomatoes.rotten")
    @Mapping(expression = "java((Integer)solrDocument.getFieldValue(\"tomatoes.viewer.meter\"))", target = "tomatoes.viewer.meter")
    @Mapping(expression = "java((Integer)solrDocument.getFieldValue(\"tomatoes.viewer.numReviews\"))", target = "tomatoes.viewer.numReviews")
    @Mapping(expression = "java((Double)solrDocument.getFieldValue(\"tomatoes.viewer.rating\"))", target = "tomatoes.viewer.rating")
    @Mapping(expression = "java((String)solrDocument.getFieldValue(\"type\"))", target = "type")
    @Mapping(expression = "java((String)solrDocument.getFieldValue(\"year\"))", target = "year")
    Movie toMovie(SolrDocument solrDocument);
}
