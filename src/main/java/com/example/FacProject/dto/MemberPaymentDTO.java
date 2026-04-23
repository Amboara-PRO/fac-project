package com.example.FacProject.dto;

import com.example.FacProject.entities.PaymentModeEnum;
import java.time.LocalDate;

public class MemberPaymentDTO {

    private String id;
    private int amount;
    private PaymentModeEnum paymentMode;
    private LocalDate creationDate;

    public MemberPaymentDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public PaymentModeEnum getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(PaymentModeEnum paymentMode) {
        this.paymentMode = paymentMode;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}