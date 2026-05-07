package com.example.FacProject.dto;

import java.math.BigDecimal;

public class CollectivityLocalStatisticsDTO {

    private MemberDescriptionDTO memberDescription;

    private BigDecimal earnedAmount;
    private BigDecimal unpaidAmount;

    private Double assiduitypePercentage;
    public CollectivityLocalStatisticsDTO() {
    }

    public MemberDescriptionDTO getMemberDescription() {
        return memberDescription;
    }

    public void setMemberDescription(MemberDescriptionDTO memberDescription) {
        this.memberDescription = memberDescription;
    }

    public BigDecimal getEarnedAmount() {
        return earnedAmount;
    }

    public void setEarnedAmount(BigDecimal earnedAmount) {
        this.earnedAmount = earnedAmount;
    }

    public BigDecimal getUnpaidAmount() {
        return unpaidAmount;
    }

    public void setUnpaidAmount(BigDecimal unpaidAmount) {
        this.unpaidAmount = unpaidAmount;
    }

    public Double getAssiduitypePercentage() { return assiduitypePercentage; }
    public void setAssiduitypePercentage(Double assiduitypePercentage) {
        this.assiduitypePercentage = assiduitypePercentage;
    }
}