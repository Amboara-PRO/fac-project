package com.example.FacProject.dto;

import com.example.FacProject.entities.ActivityStatusEnum;
import com.example.FacProject.entities.FrequencyEnum;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MembershipFeeDTO {

    public String id;
    public LocalDate eligibleFrom;
    public FrequencyEnum frequency;
    public BigDecimal amount;
    public String label;
    public ActivityStatusEnum status;

    public MembershipFeeDTO(String id, LocalDate eligibleFrom, FrequencyEnum frequency, BigDecimal amount, String label, ActivityStatusEnum status) {
        this.id = id;
        this.eligibleFrom = eligibleFrom;
        this.frequency = frequency;
        this.amount = amount;
        this.label = label;
        this.status = status;
    }

    public MembershipFeeDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public ActivityStatusEnum getStatus() {
        return status;
    }

    public void setStatus(ActivityStatusEnum status) {
        this.status = status;
    }
}