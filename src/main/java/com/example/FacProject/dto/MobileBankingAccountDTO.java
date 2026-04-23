package com.example.FacProject.dto;

public class MobileBankingAccountDTO extends FinancialAccountDTO{
    private String holderName;
    private String mobileBankingService;
    private Long mobileNumber;

    public MobileBankingAccountDTO() {
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public String getMobileBankingService() {
        return mobileBankingService;
    }

    public void setMobileBankingService(String mobileBankingService) {
        this.mobileBankingService = mobileBankingService;
    }

    public Long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(Long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
