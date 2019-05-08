package com.nano.minibank.dao;

import com.nano.minibank.entities.Account;
import com.nano.minibank.entities.TrxEntity;

public interface AccountDao {
    Account getProfile(Long acn, String cif);

    boolean addNewAccount(Account account);

    boolean isUsedUsername(String username);

    boolean isUpdatePin(String pin, String cif, Long acn);

    Long getAccountNumber(Long acn, String cif);

    Double getLastBalance(Long acn);

    boolean isAccount(Long acn);

    boolean updateBallancePlus(TrxEntity trxEntity);

    boolean updateBallanceMinus(TrxEntity trxEntity);

    boolean updateMinus(TrxEntity trxEntity);

    Long checkAccount(String cif, Long acn);
}
