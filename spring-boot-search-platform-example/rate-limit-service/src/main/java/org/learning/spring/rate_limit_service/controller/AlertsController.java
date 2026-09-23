package org.learning.spring.rate_limit_service.controller;


import org.learning.spring.rate_limit_service.dto.PrometheusAlertRequestBody;
import org.learning.spring.rate_limit_service.service.AlertService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/alerts")
public class AlertsController {

    private AlertService alertService;

    public AlertsController(AlertService alertService) {
        this.alertService = alertService;
    }

    @PostMapping
    public void handleAlert(@RequestBody PrometheusAlertRequestBody prometheusAlertRequestBody) {
        alertService.handleAlert(prometheusAlertRequestBody);
    }
}
