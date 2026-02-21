package com.foreverjava.BlackRock_Gaurav.dto;

import java.util.List;

public class ValidationRequest {
    private Double wage;
    private List<Transaction> transactions;

    public ValidationRequest() {
    }

    public ValidationRequest(Double wage, List<Transaction> transactions) {
        this.wage = wage;
        this.transactions = transactions;
    }

    public Double getWage() {
        return wage;
    }

    public void setWage(Double wage) {
        this.wage = wage;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
