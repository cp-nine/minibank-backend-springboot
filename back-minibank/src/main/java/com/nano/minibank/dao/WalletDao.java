package com.nano.minibank.dao;

import com.nano.minibank.entities.TrxEntity;
import com.nano.minibank.entities.Vwallet;
import com.nano.minibank.entities.Wallet;
import com.nano.minibank.entities.WalletAccount;

import java.util.List;

public interface WalletDao {
    boolean createWallet(Wallet wallet, Long acn);

    Wallet getWallet(Integer wid);

    List<WalletAccount> getWalletAccount(Integer id);

    boolean addAccount(WalletAccount walletAccount);

    List<Vwallet> getCustomerWallet(String cif);

    boolean unregWallet(Integer wid);

    List<Vwallet> getListWalletAccount(String cif);

    Integer login(String email, String password);

    boolean updatePlus(TrxEntity trx, Integer wid);

    boolean updateMinus(TrxEntity trx, Integer wid);

    boolean updatePlusTrx(TrxEntity trx, Integer wid);

    boolean updatePlusCt(TrxEntity trx, String ct);

    boolean updateMinusTrx(TrxEntity trx, Integer wid);

    boolean updatePassword(Integer wid, String password);

    boolean updateWalletNAme(Integer wid, String wname);

    boolean isUsedCashtag(String cashtag);

    boolean isUsedUsername(String username);

    Wallet getWalletByCasgTag(String cashtag);

    WalletAccount checkWalletAccount(Long acn);
}
