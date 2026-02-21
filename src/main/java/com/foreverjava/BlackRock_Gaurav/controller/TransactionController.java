package com.foreverjava.BlackRock_Gaurav.controller;

import com.foreverjava.BlackRock_Gaurav.dto.*;
import com.foreverjava.BlackRock_Gaurav.service.TemporalConstraintService;
import com.foreverjava.BlackRock_Gaurav.service.TransactionService;
import com.foreverjava.BlackRock_Gaurav.service.ValidationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/blackrock/challenge/v1")
public class TransactionController {

    private final TransactionService transactionService;
    private final ValidationService validationService;
    private final TemporalConstraintService temporalConstraintService;

    public TransactionController(TransactionService transactionService,
                                 ValidationService validationService,
                                 TemporalConstraintService temporalConstraintService) {
        this.transactionService = transactionService;
        this.validationService = validationService;
        this.temporalConstraintService = temporalConstraintService;
    }

    /**
     * Endpoint: /blackrock/challenge/v1/transactions:parse
     * Parse expenses and return transactions with ceiling and remanent
     */
    @PostMapping("/transactions:parse")
    public ResponseEntity<List<Transaction>> parseTransactions(@RequestBody List<Expense> expenses) {
        List<Transaction> transactions = transactionService.parseTransactions(expenses);
        return ResponseEntity.ok(transactions);
    }

    /**
     * Endpoint: /blackrock/challenge/v1/transactions:validator
     * Validate transactions based on wage and rules
     */
    @PostMapping("/transactions:validator")
    public ResponseEntity<ValidationResponse> validateTransactions(@RequestBody ValidationRequest request) {
        Map<String, List<Transaction>> result = validationService.validateTransactions(
                request.getWage(),
                request.getTransactions()
        );

        ValidationResponse response = new ValidationResponse(
                result.get("valid"),
                result.get("invalid")
        );

        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint: /blackrock/challenge/v1/transactions:filter
     * Apply temporal constraints and return valid/invalid transactions
     */
    @PostMapping("/transactions:filter")
    public ResponseEntity<FilterResponse> filterTransactions(@RequestBody FilterRequest request) {
        FilterResponse response = temporalConstraintService.applyTemporalConstraints(
                request.getQ(),
                request.getP(),
                request.getK(),
                request.getWage(),
                request.getTransactions()
        );

        return ResponseEntity.ok(response);
    }
}
