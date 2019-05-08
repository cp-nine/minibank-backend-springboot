package com.nano.minibank.controllers;

import com.nano.minibank.dao.AccountDao;
import com.nano.minibank.dao.TrxDao;
import com.nano.minibank.dao.WalletDao;
import com.nano.minibank.entities.*;
import com.nano.minibank.helpers.CommonResponse;
import com.nano.minibank.helpers.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api-v1")
public class TrxController {

    private static final String TRANSACTION_BY_ID = "/trx/{cif}";
    private static final String TRANSACTION_TOPUP = "/trx/topup";
    private static final String TRANSACTION_TOPUP_WALLET = "/trx/topup/{wid}";
    private static final String TRANSACTION_CASH = "/trx/cash";
    private static final String TRANSACTION_TRANS = "/trx/transfer";

    @Autowired
    private TrxDao trxDao;

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private WalletDao walletDao;


    @GetMapping(value = TRANSACTION_BY_ID)
    public CommonResponse<List<Vtrx>> getAllTrxCustomer(@PathVariable(value = "cif") String cif) throws UserException {

        CommonResponse<List<Vtrx>> response = new CommonResponse<>();
        List<Vtrx> trx = trxDao.getAllTrxCustomer(cif);
        if (!trx.isEmpty()) {
            response.setData(trx);
        } else {
            throw new UserException("44", "Not Found");
        }
        return response;
    }

    @GetMapping(value = "/ballance")
    public Double ballance() {

        String acn = "4296225421";
        return accountDao.getLastBalance(Long.valueOf(acn));
    }

    @PostMapping(value = TRANSACTION_TOPUP)
    public CommonResponse<TrxEntity> topUpAccount(@RequestBody TrxEntity trxEntity) throws UserException {

        CommonResponse<TrxEntity> response = new CommonResponse<>();


        if (accountDao.updateBallancePlus(trxEntity)) {
            response.setData(trxEntity);
        } else {
            throw new UserException("43", "Failed");
        }

        return response;
    }

    @PostMapping(value = TRANSACTION_TOPUP_WALLET)
    public CommonResponse<TrxEntity> topUp(@RequestBody TrxEntity trxEntity, @PathVariable(value = "wid") Integer wid) throws UserException {

        CommonResponse<TrxEntity> response = new CommonResponse<>();


        if (walletDao.updatePlus(trxEntity, wid)) {
            response.setData(trxEntity);
        } else {
            throw new UserException("40", "Bad Request");
        }

        return response;
    }

    @PostMapping(value = TRANSACTION_CASH)
    public CommonResponse<TrxEntity> cashWithdrawal(@RequestBody TrxEntity trxEntity) throws UserException {

        CommonResponse<TrxEntity> response = new CommonResponse<>();
        Double ballance = accountDao.getLastBalance(trxEntity.getAcnDebet());
        if (ballance != null){
            if (Double.parseDouble(String.valueOf(ballance))-50000 < trxEntity.getAmount()) {
                throw new UserException("34", "Your ballance is not enough");
            } else {
                if (accountDao.updateBallanceMinus(trxEntity)) {
                    response.setData(trxEntity);
                } else {
                    throw new UserException("40", "Bad Request");
                }
            }
        } else {
            throw new UserException("34", "Your ballance is not enough");
        }

        return response;
    }

    @PostMapping(value = TRANSACTION_TRANS)
    public CommonResponse<TrxEntity> transfer(@RequestBody TrxEntity trxEntity) throws UserException {

        CommonResponse<TrxEntity> response = new CommonResponse<>();
        Double ballance = accountDao.getLastBalance(trxEntity.getAcnDebet());
        if (ballance != null){
            if (Double.parseDouble(String.valueOf(ballance))-50000 < trxEntity.getAmount()) {
                throw new UserException("34", "Your ballance is not enough");
            } else {
                if (accountDao.updateBallanceMinus(trxEntity) && accountDao.updateBallancePlus(trxEntity)) {
                    response.setData(trxEntity);
                } else {
                    throw new UserException("40", "Bad Request");
                }
            }
        } else {
            throw new UserException("34", "Your ballance is not enough");
        }
        return response;
    }


    @PostMapping(value = "/trx/cash/wallet/{wid}")
    public CommonResponse<TrxEntity> cashWithdrawalWallet(
            @RequestBody TrxEntity trxEntity,
            @PathVariable(value = "wid") Integer wid) throws UserException {

        CommonResponse<TrxEntity> response = new CommonResponse<>();
        Long ballance = walletDao.getWallet(wid).getActiveBallance();
        if (ballance != null){
            if (Double.parseDouble(String.valueOf(ballance)) < trxEntity.getAmount()) {
                throw new UserException("34", "Your ballance is not enough");
            } else {
                if (walletDao.updateMinus(trxEntity, wid)) {
                    response.setData(trxEntity);
                } else {
                    throw new UserException("40", "Bad Request");
                }
            }
        } else {
            throw new UserException("34", "Your ballance is not enough");
        }

        return response;
    }


    @PostMapping(value = "/trx/trans/wallet/{wid}/{cashTag}")
    public CommonResponse<TrxEntity> transByWalletToWallet(
            @RequestBody TrxEntity trxEntity,
            @PathVariable(value = "wid") Integer wid,
            @PathVariable(value = "cashTag") String cashTag) throws UserException {

        Wallet checkWallet = walletDao.getWalletByCasgTag(cashTag);
        if (checkWallet != null){

            if (checkWallet.getType().equals("E-Banking")){
                List<WalletAccount> wa = walletDao.getWalletAccount(wid);
                return tranferWalletEbanking(wid, trxEntity, wa.get(0).getAccountNumber());
            } else {
                return tranferWallet(wid, cashTag, trxEntity);
            }

        } else {
            throw new UserException("40", "Wallet not found");
        }
    }

    public CommonResponse<TrxEntity> tranferWallet(Integer wid, String cashTag, TrxEntity trxEntity) throws UserException {

        CommonResponse<TrxEntity> response = new CommonResponse<>();
        Long ballance = walletDao.getWallet(wid).getActiveBallance();
        if (ballance != null){
            if (Double.parseDouble(String.valueOf(ballance)) < trxEntity.getAmount()) {
                throw new UserException("34", "Your ballance is not enough");
            } else {
                if (walletDao.updateMinusTrx(trxEntity, wid) && walletDao.updatePlusCt(trxEntity, cashTag)) {
                    response.setData(trxEntity);
                } else {
                    throw new UserException("40", "Transfer Failed");
                }
            }
        } else {
            throw new UserException("34", "Your ballance is not enough");
        }

        return response;

    }

    public CommonResponse<TrxEntity> tranferWalletEbanking(Integer wid, TrxEntity trxEntity, Long acn) throws UserException {

        CommonResponse<TrxEntity> response = new CommonResponse<>();
        Long ballance = walletDao.getWallet(wid).getActiveBallance();
        if (ballance != null){
            if (Double.parseDouble(String.valueOf(ballance)) < trxEntity.getAmount()) {
                throw new UserException("34", "Your ballance is not enough");
            } else {
                trxEntity.setAcnCredit(acn);
                if (walletDao.updateMinusTrx(trxEntity, wid) && accountDao.updateBallancePlus(trxEntity)) {
                    response.setData(trxEntity);
                } else {
                    throw new UserException("40", "Transfer Failed");
                }
            }
        } else {
            throw new UserException("34", "Your ballance is not enough");
        }

        return response;

    }


    @PostMapping(value = "/trx/payment/wallet/{wid}/{cashTag}")
    public CommonResponse<TrxEntity> payment(
            @RequestBody TrxEntity trxEntity,
            @PathVariable(value = "wid") Integer wid,
            @PathVariable(value = "cashTag") String cashTag) throws UserException {

        CommonResponse<TrxEntity> response = new CommonResponse<>();
        Long ballance = walletDao.getWallet(wid).getActiveBallance();
        Wallet checkwallet = walletDao.getWalletByCasgTag(cashTag);

        if (checkwallet == null || !checkwallet.getType().equals("E-Merchant")){
            throw new UserException("34", "Wallet merchant not valid");
        }else if (ballance != null){
            if (Double.parseDouble(String.valueOf(ballance)) < trxEntity.getAmount()) {
                throw new UserException("34", "Your ballance is not enough");
            } else {
                if (walletDao.updateMinusTrx(trxEntity, wid) && walletDao.updatePlusCt(trxEntity, cashTag)) {
                    response.setData(trxEntity);
                } else {
                    throw new UserException("40", "Bad Request");
                }
            }
        } else {
            throw new UserException("34", "Your ballance is not enough");
        }

        return response;
    }

    @PostMapping(value = "/trx/trans/walletaccount/{wid}")
    public CommonResponse<TrxEntity> transByWalletToWallet(
            @RequestBody TrxEntity trxEntity,
            @PathVariable(value = "wid") Integer wid) throws UserException {

        CommonResponse<TrxEntity> response = new CommonResponse<>();
        Long ballance = walletDao.getWallet(wid).getActiveBallance();
        if (ballance != null){
            if (Double.parseDouble(String.valueOf(ballance)) < trxEntity.getAmount()) {
                throw new UserException("34", "Your ballance is not enough");
            } else {
                if (walletDao.updateMinusTrx(trxEntity, wid) && accountDao.updateBallancePlus(trxEntity)) {
                    response.setData(trxEntity);
                } else {
                    throw new UserException("40", "Bad Request");
                }
            }
        } else {
            throw new UserException("34", "Your ballance is not enough");
        }

        return response;
    }

    @PostMapping(value = "/trx/topupbac/{wid}")
    public CommonResponse<TrxEntity> topUpByAccount(@RequestBody TrxEntity trxEntity,
                                                    @PathVariable(value = "wid") Integer wid) throws UserException {

        CommonResponse<TrxEntity> response = new CommonResponse<>();


        if (walletDao.updatePlusTrx(trxEntity, wid) && accountDao.updateBallanceMinus(trxEntity)) {
            response.setData(trxEntity);
        } else {
            throw new UserException("40", "Bad Request");
        }

        return response;
    }

    @GetMapping(value = "/trx/wallet/{cif}")
    public CommonResponse<List<Vtrx>> getWalletTrx(@PathVariable(value = "cif") String cif,
                                                   @RequestBody List<AccountList> al) throws UserException {

        CommonResponse<List<Vtrx>> response = new CommonResponse<>();
        List<Vtrx> trx = trxDao.getWalletTrx(cif, al);
        if (!trx.isEmpty()) {
            response.setData(trx);
        } else {
            throw new UserException("44", "Not Found");
        }
        return response;
    }

}
