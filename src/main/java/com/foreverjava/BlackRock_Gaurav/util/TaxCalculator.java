package com.foreverjava.BlackRock_Gaurav.util;

import org.springframework.stereotype.Component;

@Component
public class TaxCalculator {

    /**
     * Calculate tax based on simplified tax slabs
     * 0 to 7,00,000: 0%
     * 7,00,001 to 10,00,000: 10% on amount above 7L
     * 10,00,001 to 12,00,000: 15% on amount above 10L
     * 12,00,001 to 15,00,000: 20% on amount above 12L
     * Above 15,00,000: 30% on amount above 15L
     */
    public double calculateTax(double income) {
        double tax = 0.0;

        if (income <= 700000) {
            return 0.0;
        }

        if (income > 700000 && income <= 1000000) {
            tax = (income - 700000) * 0.10;
        } else if (income > 1000000 && income <= 1200000) {
            tax = (300000 * 0.10) + (income - 1000000) * 0.15;
        } else if (income > 1200000 && income <= 1500000) {
            tax = (300000 * 0.10) + (200000 * 0.15) + (income - 1200000) * 0.20;
        } else if (income > 1500000) {
            tax = (300000 * 0.10) + (200000 * 0.15) + (300000 * 0.20) + (income - 1500000) * 0.30;
        }

        return tax;
    }

    /**
     * Calculate NPS tax benefit
     * NPS_Deduction = min(invested, 10% of annual_income, 2,00,000)
     * Tax_Benefit = Tax(income) - Tax(income - NPS_Deduction)
     */
    public double calculateNPSTaxBenefit(double annualIncome, double invested) {
        double npsDeduction = Math.min(invested, Math.min(annualIncome * 0.10, 200000));
        double taxWithoutNPS = calculateTax(annualIncome);
        double taxWithNPS = calculateTax(annualIncome - npsDeduction);
        return taxWithoutNPS - taxWithNPS;
    }
}