package com.example.FacProject.entities;

public class MobileBankingAccountEntity extends AbstractFinancialAccount {

    private String holderName;
    private String mobileBankingService;
    private Long mobileNumber;

    public MobileBankingAccountEntity() {}

    public MobileBankingAccountEntity(String id, java.math.BigDecimal amount,
                                String holderName,
                                String mobileBankingService,
                                Long mobileNumber) {
        super(id, amount, PaymentModeEnum.MOBILE_BANKING);
        this.holderName = holderName;
        this.mobileBankingService = mobileBankingService;
        this.mobileNumber = mobileNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public String getMobileBankingService() {
        return mobileBankingService;
    }

    public Long getMobileNumber() {
        return mobileNumber;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public void setMobileBankingService(String mobileBankingService) {
        this.mobileBankingService = mobileBankingService;
    }

    public void setMobileNumber(Long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
