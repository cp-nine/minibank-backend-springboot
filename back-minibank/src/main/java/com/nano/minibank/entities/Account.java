package com.nano.minibank.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tb_account")
public class Account {

    @Id
    @Column(name = "account_number")
    private Long accountNumber;

    @Column(name = "customer_number")
    private String customerNumber;

    @Column(name = "account_name")
    private String accountName;

    @Column(name = "ballance")
    private Double ballance;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "open_date")
    private Date openDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "flag_active")
    private Integer flagActive;

    @Column(name = "flag_delete")
    private Integer flagDelete;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_number", referencedColumnName = "customer_number",
            insertable = false, updatable = false)
    @JsonIgnoreProperties("accounts")
    public Customer customer;

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

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Double getBallance() {
        return ballance;
    }

    public void setBallance(Double ballance) {
        this.ballance = ballance;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
