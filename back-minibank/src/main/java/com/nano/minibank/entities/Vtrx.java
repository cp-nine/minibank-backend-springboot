package com.nano.minibank.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "v_trx")
public class Vtrx implements Serializable {

    @Id
    @Column(name = "transaction_id")
    private Integer trxId;

    @Column(name = "customer_number")
    private String customerNumber;

    @Column(name = "transaction_code")
    private String trxCode;

    @Column(name = "acn_credit")
    private Long acnCredit;

    @Column(name = "acn_debet")
    private Long acnDebet;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "transaction_date")
    private String trxDate;

    public Integer getTrxId() {
        return trxId;
    }

    public void setTrxId(Integer trxId) {
        this.trxId = trxId;
    }

    public String getTrxCode() {
        return trxCode;
    }

    public void setTrxCode(String trxCode) {
        this.trxCode = trxCode;
    }

    public Long getAcnCredit() {
        return acnCredit;
    }

    public void setAcnCredit(Long acnCredit) {
        this.acnCredit = acnCredit;
    }

    public Long getAcnDebet() {
        return acnDebet;
    }

    public void setAcnDebet(Long acnDebet) {
        this.acnDebet = acnDebet;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getTrxDate() {
        return trxDate;
    }

    public void setTrxDate(String trxDate) {
        this.trxDate = trxDate;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }
}
