package com.example.FacProject.entities;

public class BankAccountEntity extends AbstractFinancialAccount {

    private String holderName;
    private String bankName;
    private Integer bankCode;
    private Integer branchCode;
    private Long accountNumber;
    private Integer accountKey;

    public BankAccountEntity() {}

    public BankAccountEntity(String id, java.math.BigDecimal amount,
                       String holderName,
                       String bankName,
                       Integer bankCode,
                       Integer branchCode,
                       Long accountNumber,
                       Integer accountKey) {

        super(id, amount, PaymentModeEnum.BANK_TRANSFER);
        this.holderName = holderName;
        this.bankName = bankName;
        this.bankCode = bankCode;
        this.branchCode = branchCode;
        this.accountNumber = accountNumber;
        this.accountKey = accountKey;
    }

    public String getHolderName() {
        return holderName;
    }

    public String getBankName() {
        return bankName;
    }

    public Integer getBankCode() {
        return bankCode;
    }

    public Integer getBranchCode() {
        return branchCode;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public Integer getAccountKey() {
        return accountKey;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public void setBankCode(Integer bankCode) {
        this.bankCode = bankCode;
    }

    public void setBranchCode(Integer branchCode) {
        this.branchCode = branchCode;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setAccountKey(Integer accountKey) {
        this.accountKey = accountKey;
    }
}