package org.learning.spring.spring_boot_solr_indexer.entity.mongo;

import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "movies")
public class MovieEntity {

    private Awards awards;
    private Imdb imdb;
    private Integer runtime;
    private List<String> cast;
    private List<String> countries;
    private List<String> directors;
    private List<String> genres;
    private List<String> languages;
    private List<String> writers;
    @Field(name = "_id")
    @Id
    private ObjectId id;
    @Field("fullplot")
    private String fullPlot;
    private String plot;
    private String poster;
    private String rated;
    private String released;
    private String title;
    private String type;
    private String year;
    private String lastUpdated;
    private Tomatoes tomatoes;


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Awards {
        private Integer nominations;
        private Integer wins;
        private String text;
    }


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Imdb {
        private Double rating;
        @Field("id")
        private Integer imdbId;
        private Integer votes;
    }


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Tomatoes {
        private Critic critic;
        private Integer fresh;
        private Integer rotten;
        private String lastUpdated;
        private Viewer viewer;


        @Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Viewer {
            private Double rating;
            private Integer meter;
            private Integer numReviews;
        }


        @Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Critic {
            private Double rating;
            private Integer meter;
            private Integer numReviews;
        }
    }
}
