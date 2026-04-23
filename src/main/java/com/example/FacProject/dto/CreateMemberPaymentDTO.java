package com.example.FacProject.dto;

import com.example.FacProject.entities.PaymentModeEnum;

public class CreateMemberPaymentDTO {

    private int amount;
    private String membershipFeeIdentifier;
    private String accountCreditedIdentifier;
    private PaymentModeEnum paymentMode;

    public CreateMemberPaymentDTO() {
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getMembershipFeeIdentifier() {
        return membershipFeeIdentifier;
    }

    public void setMembershipFeeIdentifier(String membershipFeeIdentifier) {
        this.membershipFeeIdentifier = membershipFeeIdentifier;
    }

    public String getAccountCreditedIdentifier() {
        return accountCreditedIdentifier;
    }

    public void setAccountCreditedIdentifier(String accountCreditedIdentifier) {
        this.accountCreditedIdentifier = accountCreditedIdentifier;
    }

    public PaymentModeEnum getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(PaymentModeEnum paymentMode) {
        this.paymentMode = paymentMode;
    }
}