package com.example.FacProject.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MembershipFeeEntity {

    private String id;
    private CollectivityEntity collectivity;
    private LocalDate eligibleFrom;
    private FrequencyEnum frequency;
    private BigDecimal amount;
    private String label;
    private ActivityStatusEnum status;
    private BigDecimal federationPercentage;

    public MembershipFeeEntity(String id, CollectivityEntity collectivity, LocalDate eligibleFrom, FrequencyEnum frequency, BigDecimal amount, String label, ActivityStatusEnum status, BigDecimal federationPercentage) {
        this.id = id;
        this.collectivity = collectivity;
        this.eligibleFrom = eligibleFrom;
        this.frequency = frequency;
        this.amount = amount;
        this.label = label;
        this.status = status;
        this.federationPercentage = federationPercentage;
    }

    public MembershipFeeEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CollectivityEntity getCollectivity() {
        return collectivity;
    }

    public void setCollectivity(CollectivityEntity collectivity) {
        this.collectivity = collectivity;
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

    public BigDecimal getFederationPercentage() {
        return federationPercentage;
    }

    public void setFederationPercentage(BigDecimal federationPercentage) {
        this.federationPercentage = federationPercentage;
    }
}