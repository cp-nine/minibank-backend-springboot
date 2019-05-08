package entity;

import java.util.Date;

public class Account {

    private Long accountNumber;

    private String customerNumber;

    private String accountName;

    private Double ballance;

    private Date openDate;

    private Date updatedAt;

    private Integer flagActive;

    private Integer flagDelete;

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
