package com.nano.minibank.dao.impl;

import com.nano.minibank.dao.AccountDao;
import com.nano.minibank.dao.TrxDao;
import com.nano.minibank.entities.Account;
import com.nano.minibank.entities.TrxEntity;
import com.nano.minibank.helpers.CodeHelper;
import com.nano.minibank.helpers.ValueHelper;
import com.nano.minibank.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class AccountDaoImpl implements AccountDao {

    @Autowired
    private AccountRepository repository;

    @Autowired
    private TrxDao trxDao;

    @Override
    public Account getProfile(Long acn, String cif){

        Account account = repository.getProfile(acn, cif);
        if (account != null)
            return account;
        else
            return null;

    }

    @Override
    public boolean addNewAccount(Account account) {

        try {
            account.setAccountNumber(generateAcn());

            TrxEntity trx = new TrxEntity();
            trx.setAcnCredit(account.getAccountNumber());
            trx.setAmount(account.getBallance());
            trx.setTrxCode("T0001");

            repository.save(account);
            trxDao.newTransaction(trx);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public boolean isUsedUsername(String username) {
//        try {
//            Account account = repository.findByUsername(username);
//            if (account != null)
//                return true;
//            else
//                return false;
//        } catch (Exception e) {
            return false;
//        }
    }

    @Override
    public boolean isUpdatePin(String pin, String cif, Long acn) {

        int isUpdated = repository.updatePassword(pin, acn, cif);
        if (isUpdated > 0)
            return true;
        else
            return false;
    }

    @Override
    public Long getAccountNumber(Long acn, String cif) {

        Account account = repository.findByAccountNumberAndCustomerNumber(acn, cif);
        if (account != null)
            return account.getAccountNumber();
        else
            return null;

    }

    @Override
    public Double getLastBalance(Long acn){

        Double balance = Double.valueOf(repository.getBallance(acn));
        if (balance != null)
            return balance;
        else
            return null;

    }

    @Override
    public boolean isAccount(Long acn){
        Optional<Account> account = repository.findById(acn);
        if (account.isPresent())
            return true;
        else
            return false;
    }

//    @Override
//    public Account login(String username, String password){
//
//        Account account = repository.findByUsernameAndPassword(username, password);
//        if (account != null)
//            return account;
//        else
//            return null;
//
//    }

    @Override
    public boolean updateBallancePlus(TrxEntity trxEntity){
        try {

            Double lastBalance = Double.valueOf(getLastBalance(trxEntity.getAcnCredit()));
            Double update = lastBalance + trxEntity.getAmount();

            repository.updateBallance(update, trxEntity.getAcnCredit());
            trxDao.newTransaction(trxEntity);
            return true;
        } catch (Exception e){
            return false;
        }

    }

    @Override
    public boolean updateBallanceMinus(TrxEntity trxEntity){
        try {

            Double lastBalance = Double.valueOf(getLastBalance(trxEntity.getAcnDebet()));
            Double update = lastBalance - trxEntity.getAmount();

            repository.updateBallance(update, trxEntity.getAcnDebet());
            trxDao.newTransaction(trxEntity);
            return true;
        } catch (Exception e){
            return false;
        }

    }

    @Override
    public boolean updateMinus(TrxEntity trxEntity){
        try {

            Double lastBalance = Double.valueOf(getLastBalance(trxEntity.getAcnDebet()));
            Double update = lastBalance - trxEntity.getAmount();

            repository.updateBallance(update, trxEntity.getAcnDebet());
            return true;
        } catch (Exception e){
            return false;
        }

    }

    public Long generateAcn(){
        int nextRow = repository.nextRow();
        int val1 = ValueHelper.getRandomNumberInRange(100000, 900000);
        int val2 = ValueHelper.getRandomNumberInRange(100000, 900000);
        int frontUniq = ValueHelper.getRandomNumberInRange(1, 9);
        String lastIndex = String.valueOf(val1 + "" + val2);
        lastIndex = lastIndex + String.valueOf(nextRow);
        return Long.parseLong(CodeHelper.makeCode(String.valueOf(frontUniq).toUpperCase(), lastIndex, 10));
    }

    @Override
    public Long checkAccount(String cif, Long acn){
        return repository.getAccountNumber(acn, cif);
    }

}
