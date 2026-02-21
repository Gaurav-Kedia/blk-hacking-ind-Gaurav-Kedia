package com.foreverjava.BlackRock_Gaurav.dto;

import java.util.List;

public class ReturnsResponse {
    private Double totalTransactionAmount;
    private Double totalCeiling;
    private List<SavingsByDate> savingsByDates;

    public ReturnsResponse() {
    }

    public ReturnsResponse(Double totalTransactionAmount, Double totalCeiling, List<SavingsByDate> savingsByDates) {
        this.totalTransactionAmount = totalTransactionAmount;
        this.totalCeiling = totalCeiling;
        this.savingsByDates = savingsByDates;
    }

    public Double getTotalTransactionAmount() {
        return totalTransactionAmount;
    }

    public void setTotalTransactionAmount(Double totalTransactionAmount) {
        this.totalTransactionAmount = totalTransactionAmount;
    }

    public Double getTotalCeiling() {
        return totalCeiling;
    }

    public void setTotalCeiling(Double totalCeiling) {
        this.totalCeiling = totalCeiling;
    }

    public List<SavingsByDate> getSavingsByDates() {
        return savingsByDates;
    }

    public void setSavingsByDates(List<SavingsByDate> savingsByDates) {
        this.savingsByDates = savingsByDates;
    }
}
