package com.foreverjava.BlackRock_Gaurav.dto;

import java.util.List;

public class FilterResponse {
    private List<Transaction> valid;
    private List<InvalidTransaction> invalid;

    public FilterResponse() {
    }

    public FilterResponse(List<Transaction> valid, List<InvalidTransaction> invalid) {
        this.valid = valid;
        this.invalid = invalid;
    }

    public List<Transaction> getValid() {
        return valid;
    }

    public void setValid(List<Transaction> valid) {
        this.valid = valid;
    }

    public List<InvalidTransaction> getInvalid() {
        return invalid;
    }

    public void setInvalid(List<InvalidTransaction> invalid) {
        this.invalid = invalid;
    }
}
