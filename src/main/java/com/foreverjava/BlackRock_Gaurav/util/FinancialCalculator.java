package com.foreverjava.BlackRock_Gaurav.util;

import org.springframework.stereotype.Component;

@Component
public class FinancialCalculator {

    /**
     * Calculate ceiling - next multiple of 100
     */
    public double calculateCeiling(double amount) {
        return Math.ceil(amount / 100.0) * 100.0;
    }

    /**
     * Calculate remanent - difference between ceiling and amount
     */
    public double calculateRemanent(double amount, double ceiling) {
        return ceiling - amount;
    }

    /**
     * Calculate compound interest
     * A = P * (1 + r/n)^(n*t)
     * For annual compounding, n = 1
     */
    public double calculateCompoundInterest(double principal, double annualRate, int years) {
        return principal * Math.pow(1 + annualRate, years);
    }

    /**
     * Calculate inflation-adjusted value
     * A_real = A / (1 + inflation)^t
     */
    public double calculateInflationAdjusted(double amount, double inflationRate, int years) {
        return amount / Math.pow(1 + inflationRate / 100.0, years);
    }

    /**
     * Calculate investment period (years until retirement)
     */
    public int calculateInvestmentPeriod(int currentAge) {
        if (currentAge >= 60) {
            return 5;
        }
        return 60 - currentAge;
    }
}