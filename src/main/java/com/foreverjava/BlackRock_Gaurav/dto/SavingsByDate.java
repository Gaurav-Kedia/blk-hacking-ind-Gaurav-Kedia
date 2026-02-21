package com.foreverjava.BlackRock_Gaurav.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SavingsByDate {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime start;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime end;
    private Double amount;
    private Double profit;
    private Double taxBenefit;
    private Double returnValue;

    public SavingsByDate() {
    }

    public SavingsByDate(LocalDateTime start, LocalDateTime end, Double amount, Double profit, Double taxBenefit) {
        this.start = start;
        this.end = end;
        this.amount = amount;
        this.profit = profit;
        this.taxBenefit = taxBenefit;
    }

    public SavingsByDate(LocalDateTime start, LocalDateTime end, Double amount, Double returnValue) {
        this.start = start;
        this.end = end;
        this.amount = amount;
        this.returnValue = returnValue;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public Double getTaxBenefit() {
        return taxBenefit;
    }

    public void setTaxBenefit(Double taxBenefit) {
        this.taxBenefit = taxBenefit;
    }

    public Double getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(Double returnValue) {
        this.returnValue = returnValue;
    }
}
