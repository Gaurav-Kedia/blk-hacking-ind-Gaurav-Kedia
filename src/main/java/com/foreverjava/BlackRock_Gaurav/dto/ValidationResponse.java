package com.foreverjava.BlackRock_Gaurav.dto;

import java.util.List;

public class ValidationResponse {
    private List<Transaction> valid;
    private List<Transaction> invalid;

    public ValidationResponse() {
    }

    public ValidationResponse(List<Transaction> valid, List<Transaction> invalid) {
        this.valid = valid;
        this.invalid = invalid;
    }

    public List<Transaction> getValid() {
        return valid;
    }

    public void setValid(List<Transaction> valid) {
        this.valid = valid;
    }

    public List<Transaction> getInvalid() {
        return invalid;
    }

    public void setInvalid(List<Transaction> invalid) {
        this.invalid = invalid;
    }
}
