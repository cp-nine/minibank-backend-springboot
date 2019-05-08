package com.nano.minibank.entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "Transaction")
@Table(name = "tb_transaction")
public class TrxEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Integer trxId;

    @Column(name = "transaction_code")
    private String trxCode;

    @Column(name = "acn_credit")
    private Long acnCredit;

    @Column(name = "acn_debet")
    private Long acnDebet;

    @Column(name = "amount")
    private Double amount;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "transaction_date")
    private Date trxDate;


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

    public Date getTrxDate() {
        return trxDate;
    }

    public void setTrxDate(Date trxDate) {
        this.trxDate = trxDate;
    }
}
