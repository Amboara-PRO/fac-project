package com.example.FacProject.dto;

import com.example.FacProject.entities.PaymentModeEnum;

import java.time.LocalDate;

public class CollectivityTransactionDTO {

    private String id;
    private LocalDate creationDate;
    private Double amount;
    private PaymentModeEnum paymentMode;

    // version simple (IDs seulement)
    private String accountCreditedId;
    private String memberDebitedId;

    public CollectivityTransactionDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public PaymentModeEnum getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(PaymentModeEnum paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getAccountCreditedId() {
        return accountCreditedId;
    }

    public void setAccountCreditedId(String accountCreditedId) {
        this.accountCreditedId = accountCreditedId;
    }

    public String getMemberDebitedId() {
        return memberDebitedId;
    }

    public void setMemberDebitedId(String memberDebitedId) {
        this.memberDebitedId = memberDebitedId;
    }
}