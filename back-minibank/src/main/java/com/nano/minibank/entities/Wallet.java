package com.nano.minibank.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "tb_wallet")
public class Wallet {

    @Id
    @Column(name = "wallet_id")
    private Integer walletId;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "wallet_name")
    private String walletName;

    @Column(name = "cash_tag")
    private String cashTag;

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "type")
    private String type;

    @Column(name = "active_ballance")
    private Long activeBallance;

    @Column(name = "place_ob")
    private String placeOb;

    @Column(name = "birth_date")
    private String birthDate;

    @Column(name = "phone_number")
    private String phoneNumber;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "flag_active", columnDefinition = "Integer default '1'")
    private Integer flagActive;

    @Column(name = "flag_delete", columnDefinition = "Integer default '0'")
    private Integer flagDelete;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getActiveBallance() {
        return activeBallance;
    }

    public void setActiveBallance(Long activeBallance) {
        this.activeBallance = activeBallance;
    }

    public String getPlaceOb() {
        return placeOb;
    }

    public void setPlaceOb(String placeOb) {
        this.placeOb = placeOb;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getFlagActive() {
        return flagActive;
    }

    public void setFlagActive(Integer flagActive) {
        this.flagActive = flagActive;
    }

    public Integer getFlagDelete() {
        return flagDelete;
    }

    public void setFlagDelete(Integer flagDelete) {
        this.flagDelete = flagDelete;
    }

}
