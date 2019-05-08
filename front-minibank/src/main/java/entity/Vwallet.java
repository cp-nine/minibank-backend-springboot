package entity;

import java.util.Date;

public class Vwallet {

    private Integer walletId;

    private String fullName;

    private String cashTag;

    private String type;

    private String createAt;

    private Long accountNumber;

    private String customerNumber;

    private Double ballance;

    public Integer getWalletId() {
        return walletId;
    }

    public void setWalletId(Integer walletId) {
        this.walletId = walletId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCashTag() {
        return cashTag;
    }

    public void setCashTag(String cashTag) {
        this.cashTag = cashTag;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public Double getBallance() {
        return ballance;
    }

    public void setBallance(Double ballance) {
        this.ballance = ballance;
    }
}
