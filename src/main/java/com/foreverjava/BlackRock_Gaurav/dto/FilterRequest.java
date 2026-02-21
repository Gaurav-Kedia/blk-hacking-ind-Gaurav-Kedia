package com.foreverjava.BlackRock_Gaurav.dto;

import java.util.List;

public class FilterRequest {
    private List<QPeriod> q;
    private List<PPeriod> p;
    private List<KPeriod> k;
    private Double wage;
    private List<Expense> transactions;

    public FilterRequest() {
    }

    public FilterRequest(List<QPeriod> q, List<PPeriod> p, List<KPeriod> k, Double wage, List<Expense> transactions) {
        this.q = q;
        this.p = p;
        this.k = k;
        this.wage = wage;
        this.transactions = transactions;
    }

    public List<QPeriod> getQ() {
        return q;
    }

    public void setQ(List<QPeriod> q) {
        this.q = q;
    }

    public List<PPeriod> getP() {
        return p;
    }

    public void setP(List<PPeriod> p) {
        this.p = p;
    }

    public List<KPeriod> getK() {
        return k;
    }

    public void setK(List<KPeriod> k) {
        this.k = k;
    }

    public Double getWage() {
        return wage;
    }

    public void setWage(Double wage) {
        this.wage = wage;
    }

    public List<Expense> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Expense> transactions) {
        this.transactions = transactions;
    }
}
