package org.learning.spring.spring_boot_solr_indexer.entity.solr;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieSolrEntity {
    private MovieSolrEntity.AwardsSolrEntity  awards;
    private MovieSolrEntity.ImdbSolrEntity  imdb;
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
    private MovieSolrEntity.TomatoesSolrEntity tomatoes;
    @JsonProperty(value = "_version_")
    private long version;
    private List<Float> vectors;


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AwardsSolrEntity  {
        private Integer nominations;
        private Integer wins;
        private String text;
    }


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ImdbSolrEntity  {
        private Double rating;
        private Integer imdbId;
        private Integer votes;
    }


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TomatoesSolrEntity  {
        private MovieSolrEntity.TomatoesSolrEntity.CriticSolrEntity critic;
        private Integer fresh;
        private Integer rotten;
        private String lastUpdated;
        private MovieSolrEntity.TomatoesSolrEntity.ViewerSolrEntity viewer;


        @Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class ViewerSolrEntity  {
            private Double rating;
            private Integer meter;
            private Integer numReviews;
        }


        @Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class CriticSolrEntity  {
            private Double rating;
            private Integer meter;
            private Integer numReviews;
        }
    }
}
