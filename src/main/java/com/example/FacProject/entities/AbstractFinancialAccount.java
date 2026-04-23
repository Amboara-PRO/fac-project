package com.example.FacProject.entities;

import java.math.BigDecimal;

public abstract class AbstractFinancialAccount implements FinancialAccountEntity {

    protected String id;
    protected BigDecimal amount;
    protected PaymentModeEnum type;

    public AbstractFinancialAccount() {}

    public AbstractFinancialAccount(String id, BigDecimal amount, PaymentModeEnum type) {
        this.id = id;
        this.amount = amount;
        this.type = type;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public PaymentModeEnum getType() {
        return type;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setType(PaymentModeEnum type) {
        this.type = type;
    }
}
