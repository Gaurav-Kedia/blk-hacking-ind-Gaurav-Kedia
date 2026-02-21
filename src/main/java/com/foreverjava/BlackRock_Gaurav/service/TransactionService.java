package com.foreverjava.BlackRock_Gaurav.service;

import com.foreverjava.BlackRock_Gaurav.dto.Expense;
import com.foreverjava.BlackRock_Gaurav.dto.Transaction;
import com.foreverjava.BlackRock_Gaurav.util.FinancialCalculator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    private final FinancialCalculator financialCalculator;

    public TransactionService(FinancialCalculator financialCalculator) {
        this.financialCalculator = financialCalculator;
    }

    /**
     * Parse expenses and calculate ceiling and remanent
     */
    public List<Transaction> parseTransactions(List<Expense> expenses) {
        List<Transaction> transactions = new ArrayList<>();

        for (Expense expense : expenses) {
            double ceiling = financialCalculator.calculateCeiling(expense.getAmount());
            double remanent = financialCalculator.calculateRemanent(expense.getAmount(), ceiling);

            transactions.add(new Transaction(
                    expense.getDate(),
                    expense.getAmount(),
                    ceiling,
                    remanent
            ));
        }

        return transactions;
    }
}
