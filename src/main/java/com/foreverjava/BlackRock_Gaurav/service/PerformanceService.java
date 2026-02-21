package com.foreverjava.BlackRock_Gaurav.service;

import com.foreverjava.BlackRock_Gaurav.dto.PerformanceResponse;
import org.springframework.stereotype.Service;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.ThreadMXBean;
import java.time.Duration;
import java.time.Instant;

@Service
public class PerformanceService {

    private final Instant startTime = Instant.now();

    /**
     * Get system performance metrics
     */
    public PerformanceResponse getPerformanceMetrics() {
        // Calculate uptime
        Duration uptime = Duration.between(startTime, Instant.now());
        String timeFormatted = String.format("%02d:%02d:%02d.%03d",
                uptime.toHours(),
                uptime.toMinutesPart(),
                uptime.toSecondsPart(),
                uptime.toMillisPart());

        // Get memory usage
        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
        long usedMemory = memoryBean.getHeapMemoryUsage().getUsed();
        double memoryMB = usedMemory / (1024.0 * 1024.0);
        String memoryFormatted = String.format("%.2f", memoryMB);

        // Get thread count
        ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();
        int threadCount = threadBean.getThreadCount();

        return new PerformanceResponse(timeFormatted, memoryFormatted, threadCount);
    }
}