package com.foreverjava.BlackRock_Gaurav.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class QPeriod {
    private Double fixed;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime start;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime end;

    public QPeriod() {
    }

    public QPeriod(Double fixed, LocalDateTime start, LocalDateTime end) {
        this.fixed = fixed;
        this.start = start;
        this.end = end;
    }

    public Double getFixed() {
        return fixed;
    }

    public void setFixed(Double fixed) {
        this.fixed = fixed;
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
}
