package org.learning.spring.spring_boot_solr_search_service.dto;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.Builder;
import lombok.AllArgsConstructor;



import java.util.List;


@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@AllArgsConstructor
public class Movie {

    private Awards awards;
    private Imdb imdb;
    private Integer runtime;
    private List<String> cast;
    private List<String> countries;
    private List<String> directors;
    private List<String> genres;
    private List<String> languages;
    private List<String> writers;
    private String id;
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


    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    public static class Awards {
        private Integer nominations;
        private Integer wins;
        private String text;
    }


    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    public static class Imdb {
        private Double rating;
        private Integer imdbId;
        private Integer votes;
    }


    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    public static class Tomatoes {
        private Critic critic;
        private Integer fresh;
        private Integer rotten;
        private String lastUpdated;
        private Viewer viewer;


        @Getter
        @Setter
        @Builder
        @AllArgsConstructor
        public static class Viewer {
            private Double rating;
            private Integer meter;
            private Integer numReviews;
        }


        @Getter
        @Setter
        @Builder
        @AllArgsConstructor
        public static class Critic {
            private Double rating;
            private Integer meter;
            private Integer numReviews;
        }
    }
}
