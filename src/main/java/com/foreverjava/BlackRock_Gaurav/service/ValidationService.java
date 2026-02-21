package com.foreverjava.BlackRock_Gaurav.service;

import com.foreverjava.BlackRock_Gaurav.dto.Transaction;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ValidationService {

    /**
     * Validate transactions based on wage and business rules
     */
    public Map<String, List<Transaction>> validateTransactions(Double wage, List<Transaction> transactions) {
        List<Transaction> valid = new ArrayList<>();
        List<Transaction> invalid = new ArrayList<>();

        for (Transaction transaction : transactions) {
            // Check for negative amounts
            if (transaction.getAmount() < 0) {
                Transaction invalidTx = new Transaction(
                        transaction.getDate(),
                        transaction.getAmount(),
                        transaction.getCeiling(),
                        transaction.getRemanent()
                );
                invalidTx.setMessage("Negative amounts are not allowed");
                invalid.add(invalidTx);
            } else {
                valid.add(transaction);
            }
        }

        Map<String, List<Transaction>> result = new HashMap<>();
        result.put("valid", valid);
        result.put("invalid", invalid);
        return result;
    }

    /**
     * Find duplicate transactions by date
     */
    public Set<LocalDateTime> findDuplicateDates(List<Transaction> transactions) {
        Set<LocalDateTime> seen = new HashSet<>();
        Set<LocalDateTime> duplicates = new HashSet<>();

        for (Transaction tx : transactions) {
            if (!seen.add(tx.getDate())) {
                duplicates.add(tx.getDate());
            }
        }

        return duplicates;
    }
}