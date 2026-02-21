package com.foreverjava.BlackRock_Gaurav.controller;

import com.foreverjava.BlackRock_Gaurav.dto.ReturnsRequest;
import com.foreverjava.BlackRock_Gaurav.dto.ReturnsResponse;
import com.foreverjava.BlackRock_Gaurav.service.IndexReturnService;
import com.foreverjava.BlackRock_Gaurav.service.NPSReturnService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blackrock/challenge/v1")
public class ReturnsController {

    private final NPSReturnService npsReturnService;
    private final IndexReturnService indexReturnService;

    public ReturnsController(NPSReturnService npsReturnService, IndexReturnService indexReturnService) {
        this.npsReturnService = npsReturnService;
        this.indexReturnService = indexReturnService;
    }

    /**
     * Endpoint: /blackrock/challenge/v1/returns:nps
     * Calculate NPS returns with tax benefits
     */
    @PostMapping("/returns:nps")
    public ResponseEntity<ReturnsResponse> calculateNPSReturns(@RequestBody ReturnsRequest request) {
        ReturnsResponse response = npsReturnService.calculateReturns(request);
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint: /blackrock/challenge/v1/returns:index
     * Calculate Index Fund returns
     */
    @PostMapping("/returns:index")
    public ResponseEntity<ReturnsResponse> calculateIndexReturns(@RequestBody ReturnsRequest request) {
        ReturnsResponse response = indexReturnService.calculateReturns(request);
        return ResponseEntity.ok(response);
    }
}
