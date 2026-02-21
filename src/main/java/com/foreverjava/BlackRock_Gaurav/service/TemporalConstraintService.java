package com.foreverjava.BlackRock_Gaurav.service;

import com.foreverjava.BlackRock_Gaurav.dto.*;
import com.foreverjava.BlackRock_Gaurav.util.FinancialCalculator;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class TemporalConstraintService {

    private final FinancialCalculator financialCalculator;
    private final ValidationService validationService;

    public TemporalConstraintService(FinancialCalculator financialCalculator, ValidationService validationService) {
        this.financialCalculator = financialCalculator;
        this.validationService = validationService;
    }

    /**
     * Apply temporal constraints (q, p, k periods) to transactions
     */
    public FilterResponse applyTemporalConstraints(
            List<QPeriod> qPeriods,
            List<PPeriod> pPeriods,
            List<KPeriod> kPeriods,
            Double wage,
            List<Expense> expenses) {

        List<Transaction> valid = new ArrayList<>();
        List<InvalidTransaction> invalid = new ArrayList<>();

        // Convert expenses to transactions
        List<Transaction> transactions = new ArrayList<>();
        for (Expense expense : expenses) {
            double ceiling = financialCalculator.calculateCeiling(expense.getAmount());
            double remanent = financialCalculator.calculateRemanent(expense.getAmount(), ceiling);
            transactions.add(new Transaction(expense.getDate(), expense.getAmount(), ceiling, remanent));
        }

        // Find duplicates
        Set<LocalDateTime> duplicates = validationService.findDuplicateDates(transactions);

        // Track processed dates to handle duplicates
        Set<LocalDateTime> processedDates = new HashSet<>();

        for (Transaction transaction : transactions) {
            LocalDateTime date = transaction.getDate();

            // Check for duplicates
            if (duplicates.contains(date)) {
                if (processedDates.contains(date)) {
                    invalid.add(new InvalidTransaction(date, "Duplicate transaction"));
                    continue;
                }
                processedDates.add(date);
            }

            // Check for negative amounts
            if (transaction.getAmount() < 0) {
                invalid.add(new InvalidTransaction(date, "Negative amounts are not allowed"));
                continue;
            }

            // Apply q period rules (fixed amount override)
            double remanent = transaction.getRemanent();
            remanent = applyQPeriodRules(date, remanent, qPeriods);

            // Apply p period rules (extra amount addition)
            remanent = applyPPeriodRules(date, remanent, pPeriods);

            // Check if in k period
            boolean inKPeriod = isInKPeriod(date, kPeriods);

            Transaction validTx = new Transaction(
                    date,
                    transaction.getAmount(),
                    transaction.getCeiling(),
                    remanent
            );
            validTx.setInKPeriod(inKPeriod);
            valid.add(validTx);
        }

        return new FilterResponse(valid, invalid);
    }

    /**
     * Apply q period rules - fixed amount override
     * If multiple q periods match: use the one that starts latest
     * If they start on the same date, use the first one in the list
     */
    private double applyQPeriodRules(LocalDateTime date, double remanent, List<QPeriod> qPeriods) {
        if (qPeriods == null || qPeriods.isEmpty()) {
            return remanent;
        }

        QPeriod matchingPeriod = null;

        for (QPeriod period : qPeriods) {
            if (isDateInPeriod(date, period.getStart(), period.getEnd())) {
                if (matchingPeriod == null ||
                        period.getStart().isAfter(matchingPeriod.getStart())) {
                    matchingPeriod = period;
                }
            }
        }

        if (matchingPeriod != null) {
            return matchingPeriod.getFixed();
        }

        return remanent;
    }

    /**
     * Apply p period rules - extra amount addition
     * If multiple p periods match: add all their extra amounts
     */
    private double applyPPeriodRules(LocalDateTime date, double remanent, List<PPeriod> pPeriods) {
        if (pPeriods == null || pPeriods.isEmpty()) {
            return remanent;
        }

        double totalExtra = 0.0;

        for (PPeriod period : pPeriods) {
            if (isDateInPeriod(date, period.getStart(), period.getEnd())) {
                totalExtra += period.getExtra();
            }
        }

        return remanent + totalExtra;
    }

    /**
     * Check if date is in any k period
     */
    private boolean isInKPeriod(LocalDateTime date, List<KPeriod> kPeriods) {
        if (kPeriods == null || kPeriods.isEmpty()) {
            return true; // If no k periods defined, all transactions are valid
        }

        for (KPeriod period : kPeriods) {
            if (isDateInPeriod(date, period.getStart(), period.getEnd())) {
                return true;
            }
        }

        return false;
    }

    /**
     * Check if date is within period (inclusive)
     */
    private boolean isDateInPeriod(LocalDateTime date, LocalDateTime start, LocalDateTime end) {
        return !date.isBefore(start) && !date.isAfter(end);
    }

    /**
     * Calculate savings by k periods
     */
    public Map<KPeriod, Double> calculateSavingsByKPeriods(
            List<Transaction> transactions,
            List<KPeriod> kPeriods) {

        Map<KPeriod, Double> savingsByPeriod = new LinkedHashMap<>();

        if (kPeriods == null || kPeriods.isEmpty()) {
            return savingsByPeriod;
        }

        for (KPeriod period : kPeriods) {
            double total = 0.0;

            for (Transaction tx : transactions) {
                if (isDateInPeriod(tx.getDate(), period.getStart(), period.getEnd())) {
                    total += tx.getRemanent();
                }
            }

            savingsByPeriod.put(period, total);
        }

        return savingsByPeriod;
    }
}