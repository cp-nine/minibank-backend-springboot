package com.nano.minibank.dao.impl;

import com.nano.minibank.dao.AccountDao;
import com.nano.minibank.dao.TrxDao;
import com.nano.minibank.dao.WalletDao;
import com.nano.minibank.entities.TrxEntity;
import com.nano.minibank.entities.Vwallet;
import com.nano.minibank.entities.Wallet;
import com.nano.minibank.entities.WalletAccount;
import com.nano.minibank.helpers.CodeHelper;
import com.nano.minibank.helpers.ValueHelper;
import com.nano.minibank.repositories.VwalletRepository;
import com.nano.minibank.repositories.WalletAccountRepository;
import com.nano.minibank.repositories.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class WalletDaoImpl implements WalletDao {

    @Autowired
    private WalletRepository repository;

    @Autowired
    private WalletAccountRepository waRepository;

    @Autowired
    private VwalletRepository vwalletRepository;

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private TrxDao trxDao;

    @Override
    public boolean createWallet(Wallet wallet, Long acn) {
        try {
            WalletAccount account = new WalletAccount();
            account.setAccountNumber(acn);

            wallet.setWalletId(getCode());
            account.setWalletId(wallet.getWalletId());

            repository.save(wallet);
            waRepository.save(account);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Wallet getWallet(Integer wid) {
        return repository.findById(wid).orElse(null);
    }

    // get kode
    public Integer getCode() {

        int last = repository.nextRow();
        int val1 = ValueHelper.getRandomNumberInRange(100000, 9000000);
        int val2 = ValueHelper.getRandomNumberInRange(1, 9);
        String lastIndex = String.valueOf(val1);

        lastIndex = lastIndex + last;

        return Integer.parseInt(CodeHelper.makeCode(String.valueOf(val2), lastIndex, 5));

    }

    @Override
    public List<WalletAccount> getWalletAccount(Integer id) {
        return waRepository.findByWalletId(id);
    }

    @Override
    public boolean addAccount(WalletAccount walletAccount) {
        try {
            waRepository.save(walletAccount);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Vwallet> getCustomerWallet(String cif) {
        return vwalletRepository.findByCustomerNumber(cif);
    }

    @Override
    public boolean unregWallet(Integer wid){
        if (repository.unreg(wid) > 0){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Vwallet> getListWalletAccount(String cif){
        return vwalletRepository.getActiveWallet(cif);
    }

    @Override
    public Integer login(String cashtag, String password){
        Wallet wallet = repository.findByCashTagAndPassword(cashtag, password);
        if (wallet != null){
            return wallet.getWalletId();
        } else {
            return null;
        }
    }

    @Override
    public boolean updatePlus(TrxEntity trx, Integer wid){
        try {
            Wallet wallet = repository.findById(wid).orElse(null);
            if (wallet != null){
                wallet.setActiveBallance(wallet.getActiveBallance() + trx.getAmount().longValue());
                repository.save(wallet);
                accountDao.updateMinus(trx);
                trxDao.newTransaction(trx);
                return true;
            } else {
              return false;
            }

        } catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean updateMinus(TrxEntity trx, Integer wid){
        try {
            Wallet wallet = repository.findById(wid).orElse(null);
            if (wallet != null){
                wallet.setActiveBallance(wallet.getActiveBallance() - trx.getAmount().longValue());
                repository.save(wallet);
                trxDao.newTransaction(trx);
                return true;
            } else {
                return false;
            }

        } catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean updatePlusTrx(TrxEntity trx, Integer wid){
        try {
            Wallet wallet = repository.findById(wid).orElse(null);
            if (wallet != null){
                wallet.setActiveBallance(wallet.getActiveBallance() + trx.getAmount().longValue());
                repository.save(wallet);
                return true;
            } else {
                return false;
            }

        } catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean updatePlusCt(TrxEntity trx, String ct){
        try {
            Wallet wallet = repository.findByCashTag(ct);
            List<WalletAccount> wa = waRepository.findByWalletId(wallet.getWalletId());
            if (wallet != null && wa.size() > 0){
                wallet.setActiveBallance(wallet.getActiveBallance() + trx.getAmount().longValue());
                trx.setAcnCredit(wa.get(0).getAccountNumber());
                trxDao.newTransaction(trx);
                repository.save(wallet);
                return true;
            } else {
                return false;
            }

        } catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean updateMinusTrx(TrxEntity trx, Integer wid){
        try {
            Wallet wallet = repository.findById(wid).orElse(null);
            if (wallet != null){
                wallet.setActiveBallance(wallet.getActiveBallance() - trx.getAmount().longValue());
                repository.save(wallet);
                return true;
            } else {
                return false;
            }

        } catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean updatePassword(Integer wid, String password){
        Wallet current = repository.findById(wid).orElse(null);

        if (current != null){
            current.setPassword(password);
            repository.save(current);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateWalletNAme(Integer wid, String wname){
        Wallet current = repository.findById(wid).orElse(null);

        if (current != null){
            current.setWalletName(wname);
            repository.save(current);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isUsedCashtag(String cashtag){
        boolean isUsed = false;
        Wallet current = repository.findByCashTag(cashtag);
        if (current!=null){
            isUsed =true;
        }

        return isUsed;
    }

    @Override
    public boolean isUsedUsername(String username){
        boolean isUsed = false;
        Wallet current = repository.findByUsername(username);
        if (current!=null){
            isUsed =true;
        }

        return isUsed;
    }

    @Override
    public Wallet getWalletByCasgTag(String cashtag){
        return repository.findByCashTag(cashtag);
    }

    @Override
    public WalletAccount checkWalletAccount(Long acn){
        return waRepository.findByAccountNumber(acn);
    }
}
