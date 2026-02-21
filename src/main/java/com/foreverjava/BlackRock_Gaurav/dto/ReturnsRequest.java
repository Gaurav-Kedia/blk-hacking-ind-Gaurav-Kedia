package com.foreverjava.BlackRock_Gaurav.dto;

import java.util.List;

public class ReturnsRequest {
    private Integer age;
    private Double wage;
    private Double inflation;
    private List<QPeriod> q;
    private List<PPeriod> p;
    private List<KPeriod> k;
    private List<Expense> transactions;

    public ReturnsRequest() {
    }

    public ReturnsRequest(Integer age, Double wage, Double inflation, List<QPeriod> q, List<PPeriod> p, List<KPeriod> k, List<Expense> transactions) {
        this.age = age;
        this.wage = wage;
        this.inflation = inflation;
        this.q = q;
        this.p = p;
        this.k = k;
        this.transactions = transactions;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getWage() {
        return wage;
    }

    public void setWage(Double wage) {
        this.wage = wage;
    }

    public Double getInflation() {
        return inflation;
    }

    public void setInflation(Double inflation) {
        this.inflation = inflation;
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

    public List<Expense> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Expense> transactions) {
        this.transactions = transactions;
    }
}
