package dao;

import dto.CommonResponse;
import entity.Vwallet;
import entity.Wallet;
import entity.WalletAccount;

import java.util.List;

public interface WalletWsDao {
    List<Vwallet> getWalletCustomer(String cif);

    Wallet getWalletProfile(Integer wid);

    CommonResponse<Wallet> unregWallet(Integer wid);

    List<WalletAccount> getWalletAccount(Integer wid);

    CommonResponse<Wallet> addWallet(Wallet wallet, Long acn);

    CommonResponse<WalletAccount> addWalletAccount(WalletAccount walletAccount);
}
