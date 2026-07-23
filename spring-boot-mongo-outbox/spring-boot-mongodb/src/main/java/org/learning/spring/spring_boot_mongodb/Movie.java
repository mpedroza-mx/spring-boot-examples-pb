package org.learning.spring.spring_boot_mongodb;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document(collection = "movies")
public class Movie {

    private Awards awards;
    private Imdb imdb;
    private Integer metaCritic;
    private Integer runtime;
    private List<String> cast;
    private List<String> countries;
    private List<String> directors;
    private List<String> genres;
    private List<String> languages;
    private List<String> writers;
    @JsonProperty("_id")
    @Id
    @EqualsAndHashCode.Include
    private ObjectId id;
    private String fullPlot;
    private String plot;
    private String poster;
    private String rated;
    private String released;
    private String title;
    private String type;
    private String year;
    private Tomatoes tomatoes;


    @Getter
    @Setter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Awards {
        private Integer nominations;
        private Integer wins;
        private String text;
    }


    @Getter
    @Setter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Imdb {
        private Double rating;
        private Integer id;
        private Integer votes;
    }


    @Getter
    @Setter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Tomatoes {
        private Critic critic;
        private Integer fresh;
        private Integer rotten;
        private String lastUpdated;
        private String production;
        private Viewer viewer;


        @Getter
        @Setter
        @Builder
        @AllArgsConstructor(access = AccessLevel.PROTECTED)
        @NoArgsConstructor(access = AccessLevel.PROTECTED)
        public static class Viewer {
            private Double rating;
            private Integer meter;
            private Integer numReviews;
        }


        @Getter
        @Setter
        @Builder
        @AllArgsConstructor(access = AccessLevel.PROTECTED)
        @NoArgsConstructor(access = AccessLevel.PROTECTED)
        public static class Critic {
            private Double rating;
            private Integer meter;
            private Integer numReviews;
        }
    }
}
