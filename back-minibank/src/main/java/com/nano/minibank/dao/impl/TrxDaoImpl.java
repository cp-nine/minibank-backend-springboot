package com.nano.minibank.dao.impl;

import com.nano.minibank.dao.TrxDao;
import com.nano.minibank.entities.AccountList;
import com.nano.minibank.entities.TrxEntity;
import com.nano.minibank.entities.Vtrx;
import com.nano.minibank.repositories.TrxRepository;
import com.nano.minibank.repositories.VtrxRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class TrxDaoImpl implements TrxDao {

    @Autowired
    private TrxRepository repository;

    @Autowired
    private VtrxRepository vtrxRepository;

    @Override
    public boolean newTransaction(TrxEntity trxEntity){

        try {
            repository.save(trxEntity);
            return true;
        } catch (Exception e){
            return false;
        }

    }

    @Override
    public List<Vtrx> getAllTrxCustomer(String cif){

        return vtrxRepository.getTransaction(cif);

    }

    @Override
    public List<Vtrx> getWalletTrx(String cif, List<AccountList> acountList){

        List<Vtrx> list = new ArrayList<>();

        for (AccountList al: acountList) {
           List<Vtrx> alst = vtrxRepository.getTrxWallet(cif, al.getAccountNumber());
            for (Vtrx tr: alst) {
                list.add(tr);
            }
        }

        return list;

    }

}
