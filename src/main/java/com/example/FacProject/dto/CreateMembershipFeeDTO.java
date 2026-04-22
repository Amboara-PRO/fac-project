package com.example.FacProject.dto;

import com.example.FacProject.entities.FrequencyEnum;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CreateMembershipFeeDTO {

    public LocalDate eligibleFrom;
    public FrequencyEnum frequency;
    public BigDecimal amount;
    public String label;

    public CreateMembershipFeeDTO(LocalDate eligibleFrom, FrequencyEnum frequency, BigDecimal amount, String label) {
        this.eligibleFrom = eligibleFrom;
        this.frequency = frequency;
        this.amount = amount;
        this.label = label;
    }

    public CreateMembershipFeeDTO() {
    }

    public LocalDate getEligibleFrom() {
        return eligibleFrom;
    }

    public void setEligibleFrom(LocalDate eligibleFrom) {
        this.eligibleFrom = eligibleFrom;
    }

    public FrequencyEnum getFrequency() {
        return frequency;
    }

    public void setFrequency(FrequencyEnum frequency) {
        this.frequency = frequency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}