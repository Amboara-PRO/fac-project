package com.example.FacProject.entities;

import java.math.BigDecimal;

public interface FinancialAccountEntity {
    String getId();
    BigDecimal getAmount();
    PaymentModeEnum getType();
}
