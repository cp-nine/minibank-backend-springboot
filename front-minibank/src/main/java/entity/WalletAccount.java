package entity;

public class WalletAccount {

    private Integer waId;

    private Integer walletId;

    private Long accountNumber;

    public Account account;


    public Integer getWaId() {
        return waId;
    }

    public Integer getWalletId() {
        return walletId;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public Account getAccount() {
        return account;
    }

    public void setWaId(Integer waId) {
        this.waId = waId;
    }

    public void setWalletId(Integer walletId) {
        this.walletId = walletId;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
