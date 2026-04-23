package com.example.FacProject.dto;

public abstract class FinancialAccountDTO {
    private String id;
    private double amount;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
}