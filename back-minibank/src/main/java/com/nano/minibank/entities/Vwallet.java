package com.nano.minibank.entities;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "v_wallet_account")
public class Vwallet {

    @Id
    @Column(name = "wallet_id")
    private Integer walletId;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "wallet_name")
    private String walletName;

    @Column(name = "cash_tag")
    private String cashTag;

    @Column(name = "type")
    private String type;

    @Column(name = "created_at")
    private String createAt;

    @Column(name = "account_number")
    private Long accountNumber;

    @Column(name = "customer_number")
    private String customerNumber;

    @Column(name = "ballance")
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

    public String getWalletName() {
        return walletName;
    }

    public void setWalletName(String walletName) {
        this.walletName = walletName;
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
