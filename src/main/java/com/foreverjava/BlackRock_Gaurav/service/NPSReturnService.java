package com.foreverjava.BlackRock_Gaurav.service;

import com.foreverjava.BlackRock_Gaurav.dto.*;
import com.foreverjava.BlackRock_Gaurav.util.FinancialCalculator;
import com.foreverjava.BlackRock_Gaurav.util.TaxCalculator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class NPSReturnService {

    private final FinancialCalculator financialCalculator;
    private final TaxCalculator taxCalculator;
    private final TemporalConstraintService temporalConstraintService;
    private final TransactionService transactionService;

    private static final double NPS_ANNUAL_RATE = 0.0711;

    public NPSReturnService(FinancialCalculator financialCalculator,
                            TaxCalculator taxCalculator,
                            TemporalConstraintService temporalConstraintService,
                            TransactionService transactionService) {
        this.financialCalculator = financialCalculator;
        this.taxCalculator = taxCalculator;
        this.temporalConstraintService = temporalConstraintService;
        this.transactionService = transactionService;
    }

    /**
     * Calculate NPS returns with tax benefits
     */
    public ReturnsResponse calculateReturns(ReturnsRequest request) {
        // Parse transactions
        List<Transaction> allTransactions = transactionService.parseTransactions(request.getTransactions());

        // Apply temporal constraints
        List<Transaction> validTransactions = applyConstraints(
                allTransactions,
                request.getQ(),
                request.getP()
        );

        // Calculate total transaction amount and ceiling
        double totalAmount = validTransactions.stream()
                .mapToDouble(Transaction::getAmount)
                .sum();
        double totalCeiling = validTransactions.stream()
                .mapToDouble(Transaction::getCeiling)
                .sum();

        // Calculate savings by k periods
        Map<KPeriod, Double> savingsByPeriod = temporalConstraintService.calculateSavingsByKPeriods(
                validTransactions,
                request.getK()
        );

        // Calculate returns for each k period
        List<SavingsByDate> savingsByDates = new ArrayList<>();
        int investmentPeriod = financialCalculator.calculateInvestmentPeriod(request.getAge());
        double annualIncome = request.getWage() * 12;
        double inflationRate = request.getInflation();

        for (Map.Entry<KPeriod, Double> entry : savingsByPeriod.entrySet()) {
            KPeriod period = entry.getKey();
            Double amount = entry.getValue();

            // Calculate compound interest
            double futureValue = financialCalculator.calculateCompoundInterest(
                    amount,
                    NPS_ANNUAL_RATE,
                    investmentPeriod
            );

            // Calculate inflation-adjusted value
            double realValue = financialCalculator.calculateInflationAdjusted(
                    futureValue,
                    inflationRate,
                    investmentPeriod
            );

            // Calculate profit
            double profit = realValue - amount;

            // Calculate tax benefit
            double taxBenefit = taxCalculator.calculateNPSTaxBenefit(annualIncome, amount);

            savingsByDates.add(new SavingsByDate(
                    period.getStart(),
                    period.getEnd(),
                    amount,
                    profit,
                    taxBenefit
            ));
        }

        return new ReturnsResponse(totalAmount, totalCeiling, savingsByDates);
    }

    /**
     * Apply q and p period constraints to transactions
     */
    private List<Transaction> applyConstraints(
            List<Transaction> transactions,
            List<QPeriod> qPeriods,
            List<PPeriod> pPeriods) {

        List<Transaction> result = new ArrayList<>();

        for (Transaction tx : transactions) {
            // Skip negative amounts
            if (tx.getAmount() < 0) {
                continue;
            }

            double remanent = tx.getRemanent();

            // Apply q period rules
            remanent = applyQPeriodRules(tx.getDate(), remanent, qPeriods);

            // Apply p period rules
            remanent = applyPPeriodRules(tx.getDate(), remanent, pPeriods);

            Transaction newTx = new Transaction(
                    tx.getDate(),
                    tx.getAmount(),
                    tx.getCeiling(),
                    remanent
            );
            result.add(newTx);
        }

        return result;
    }

    private double applyQPeriodRules(java.time.LocalDateTime date, double remanent, List<QPeriod> qPeriods) {
        if (qPeriods == null || qPeriods.isEmpty()) {
            return remanent;
        }

        QPeriod matchingPeriod = null;

        for (QPeriod period : qPeriods) {
            if (!date.isBefore(period.getStart()) && !date.isAfter(period.getEnd())) {
                if (matchingPeriod == null || period.getStart().isAfter(matchingPeriod.getStart())) {
                    matchingPeriod = period;
                }
            }
        }

        if (matchingPeriod != null) {
            return matchingPeriod.getFixed();
        }

        return remanent;
    }

    private double applyPPeriodRules(java.time.LocalDateTime date, double remanent, List<PPeriod> pPeriods) {
        if (pPeriods == null || pPeriods.isEmpty()) {
            return remanent;
        }

        double totalExtra = 0.0;

        for (PPeriod period : pPeriods) {
            if (!date.isBefore(period.getStart()) && !date.isAfter(period.getEnd())) {
                totalExtra += period.getExtra();
            }
        }

        return remanent + totalExtra;
    }
}