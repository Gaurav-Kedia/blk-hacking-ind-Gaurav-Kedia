package com.foreverjava.BlackRock_Gaurav.controller;

import com.foreverjava.BlackRock_Gaurav.dto.PerformanceResponse;
import com.foreverjava.BlackRock_Gaurav.service.PerformanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blackrock/challenge/v1")
public class PerformanceController {

    private final PerformanceService performanceService;

    public PerformanceController(PerformanceService performanceService) {
        this.performanceService = performanceService;
    }

    /**
     * Endpoint: /blackrock/challenge/v1/performance
     * Get system performance metrics
     */
    @GetMapping("/performance")
    public ResponseEntity<PerformanceResponse> getPerformance() {
        PerformanceResponse response = performanceService.getPerformanceMetrics();
        return ResponseEntity.ok(response);
    }
}
