package entity;

import java.util.Date;

public class Vtrx {

    private Integer trxId;

    private String customerNumber;

    private String trxCode;

    private Long acnCredit;

    private Long acnDebet;

    private Double amount;

    private String trxDate;


    public Integer getTrxId() {
        return trxId;
    }

    public void setTrxId(Integer trxId) {
        this.trxId = trxId;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
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
}
