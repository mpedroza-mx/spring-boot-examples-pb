package org.learning.spring.rate_limit_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PrometheusAlertRequestBody {
    private String version;
    private String groupKey;
    private String truncatedAlerts;
    private String status;
    private String receiver;
    private Labels groupLabels;
    private Labels commonLabels;
    private Annotations commonAnnotations;
    private String externalURL;
    @JsonProperty("notification_reason")
    private String notificationReason;
    private List<Alerts> alerts;



    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Alerts {
        private String status;
        private Labels labels;
        private Annotations annotations;
        private String startsAt;
        private String endsAt;
        private String generatorURL;
        @JsonProperty("fingerprint")
        private String fingerPrint;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Labels {
        @JsonProperty("alertname")
        private String alertName;
        private String instance;
        private String severity;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Annotations {
        private String description;
        private String summary;
    }
}
