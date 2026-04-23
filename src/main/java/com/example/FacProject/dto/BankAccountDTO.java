package com.example.FacProject.dto;

public class BankAccountDTO extends FinancialAccountDTO {
    private String holderName;
    private String bankName;
    private Integer bankCode;
    private Integer bankBranchCode;
    private Integer bankAccountNumber;
    private Integer bankAccountKey;

    public BankAccountDTO() {
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Integer getBankCode() {
        return bankCode;
    }

    public void setBankCode(Integer bankCode) {
        this.bankCode = bankCode;
    }

    public Integer getBankBranchCode() {
        return bankBranchCode;
    }

    public void setBankBranchCode(Integer bankBranchCode) {
        this.bankBranchCode = bankBranchCode;
    }

    public Integer getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(Integer bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public Integer getBankAccountKey() {
        return bankAccountKey;
    }

    public void setBankAccountKey(Integer bankAccountKey) {
        this.bankAccountKey = bankAccountKey;
    }
}