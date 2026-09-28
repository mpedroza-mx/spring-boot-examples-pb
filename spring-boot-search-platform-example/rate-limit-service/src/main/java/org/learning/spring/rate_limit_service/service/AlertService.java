package org.learning.spring.rate_limit_service.service;

import org.learning.spring.rate_limit_service.dto.PrometheusAlertRequestBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class AlertService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AlertService.class);
    private final ReactiveRedisTemplate<String, Boolean> reactiveRedisTemplate;


    public AlertService(ReactiveRedisTemplate reactiveRedisTemplate) {
        this.reactiveRedisTemplate = reactiveRedisTemplate;
    }

    public void handleAlert(PrometheusAlertRequestBody prometheusAlertRequestBody) {

        prometheusAlertRequestBody.getAlerts().forEach(alert -> {
            if (alert.getStatus().equals("firing")) {

                Boolean rateLimitEnabled = reactiveRedisTemplate.opsForValue().set(alert.getLabels().getAlertName(), true, Duration.ofMinutes(4))
                        .block();

               if (rateLimitEnabled != null && rateLimitEnabled){
                   LOGGER.info("Enable rate limit for: {}", alert.getLabels().getAlertName());
               }else{
                   LOGGER.error("Error while trying to activate the rate limit for alert {}", alert.getLabels().getAlertName());
               }
            } else if (alert.getStatus().equals("resolved")) {
                LOGGER.info("Deactivate rate limit for alert: {}", alert.getLabels().getAlertName());
            }
        });

    }

}
