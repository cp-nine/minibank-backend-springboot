package entity;

import java.util.Date;

public class TrxEntity {

    private Integer trxId;

    private String trxCode;

    private Long acnCredit;

    private Long acnDebet;

    private Double amount;

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
