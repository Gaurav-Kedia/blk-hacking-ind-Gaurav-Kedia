package com.foreverjava.BlackRock_Gaurav.dto;

public class PerformanceResponse {
    private String time;
    private String memory;
    private Integer threads;

    public PerformanceResponse() {
    }

    public PerformanceResponse(String time, String memory, Integer threads) {
        this.time = time;
        this.memory = memory;
        this.threads = threads;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public Integer getThreads() {
        return threads;
    }

    public void setThreads(Integer threads) {
        this.threads = threads;
    }
}
