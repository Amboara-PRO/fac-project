package com.example.FacProject.entities;


public class CashAccountEntity extends AbstractFinancialAccount {

    public CashAccountEntity() {}

    public CashAccountEntity(String id, java.math.BigDecimal amount) {
        super(id, amount, PaymentModeEnum.CASH);
    }
}
