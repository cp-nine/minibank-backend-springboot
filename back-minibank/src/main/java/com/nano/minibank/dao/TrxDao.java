package com.nano.minibank.dao;

import com.nano.minibank.entities.AccountList;
import com.nano.minibank.entities.TrxEntity;
import com.nano.minibank.entities.Vtrx;

import java.util.List;

public interface TrxDao {
    boolean newTransaction(TrxEntity trxEntity);

    List<Vtrx> getAllTrxCustomer(String cif);

    List<Vtrx> getWalletTrx(String cif, List<AccountList> al);
}
