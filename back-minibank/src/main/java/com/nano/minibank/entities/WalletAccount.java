package com.nano.minibank.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "tb_wallet_account")
public class WalletAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wa_id")
    private Integer waId;

    @Column(name = "wallet_id")
    private Integer walletId;

    @Column(name = "account_number")
    private Long accountNumber;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_number", referencedColumnName = "account_number",
            insertable = false, updatable = false)
    @JsonIgnoreProperties("customer")
    public Account account;

    public Integer getWaId() {
        return waId;
    }

    public void setWaId(Integer waId) {
        this.waId = waId;
    }

    public Integer getWalletId() {
        return walletId;
    }

    public void setWalletId(Integer walletId) {
        this.walletId = walletId;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

}
