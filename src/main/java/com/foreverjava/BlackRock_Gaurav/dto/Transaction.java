package com.foreverjava.BlackRock_Gaurav.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Transaction {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;
    private Double amount;
    private Double ceiling;
    private Double remanent;
    private Boolean inKPeriod;
    private String message;

    public Transaction() {
    }

    public Transaction(LocalDateTime date, Double amount, Double ceiling, Double remanent) {
        this.date = date;
        this.amount = amount;
        this.ceiling = ceiling;
        this.remanent = remanent;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getCeiling() {
        return ceiling;
    }

    public void setCeiling(Double ceiling) {
        this.ceiling = ceiling;
    }

    public Double getRemanent() {
        return remanent;
    }

    public void setRemanent(Double remanent) {
        this.remanent = remanent;
    }

    public Boolean getInKPeriod() {
        return inKPeriod;
    }

    public void setInKPeriod(Boolean inKPeriod) {
        this.inKPeriod = inKPeriod;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
