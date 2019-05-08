package com.nano.minibank.controllers;

import com.nano.minibank.dao.AccountDao;
import com.nano.minibank.dao.CustomerDao;
import com.nano.minibank.dao.WalletDao;
import com.nano.minibank.entities.*;
import com.nano.minibank.helpers.CommonResponse;
import com.nano.minibank.helpers.EntityNotFoundException;
import com.nano.minibank.helpers.UserException;
import com.nano.minibank.helpers.ValueHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api-v1")
public class WalletController {

    private static final String WALLET_ACN = "/wallet/{acn}/{cif}";
    private static final String WALLET_CIF = "/wallet/list/{cif}";
    private static final String WALLET_BY_ID = "/wallet/{wid}";
    private static final String WALLET_LIST_BY_ID = "/wallet/list-account/{cif}";
    private static final String WALLET_UNREG = "/wallet/unreg/{wid}";
    private static final String WALLET_ACCOUNT = "/wallet/account";
    private static final String WALLET_ACCOUNT_BY_ID = "/wallet/account/{waid}";

    @Autowired
    private WalletDao walletDao;

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private CustomerDao customerDao;

    @GetMapping(value = WALLET_BY_ID)
    public CommonResponse<Wallet> getProfile(@PathVariable(value = "wid") Integer wid) throws UserException {

        CommonResponse<Wallet> response = new CommonResponse<>();
        Wallet wallet = walletDao.getWallet(wid);
        if (wallet != null) {
            response.setData(wallet);
        } else {
            throw new UserException("44", "Wallet Nopt Found");
        }
        return response;
    }

    @GetMapping(value = WALLET_ACCOUNT_BY_ID)
    public CommonResponse<List<WalletAccount>> getWalletAccount(@PathVariable(name = "waid") Integer waid) throws UserException {

        CommonResponse<List<WalletAccount>> response = new CommonResponse<>();
        List<WalletAccount> list = walletDao.getWalletAccount(waid);
        if (!list.isEmpty()) {
            response.setData(list);
        } else {
            throw new UserException("44", "Wallet Nopt Found");
        }
        return response;
    }

    @GetMapping(value = WALLET_LIST_BY_ID)
    public CommonResponse<List<Vwallet>> getListWalletAccount(@PathVariable(name = "cif") String cif) throws UserException {

        CommonResponse<List<Vwallet>> response = new CommonResponse<>();
        List<Vwallet> list = walletDao.getListWalletAccount(cif);
        if (!list.isEmpty()) {
            response.setData(list);
        } else {
            throw new UserException("44", "Wallet Nopt Found");
        }
        return response;
    }

    @GetMapping(value = WALLET_CIF)
    public CommonResponse<List<Vwallet>> getCustomerWallet(@PathVariable(name = "cif") String cif) throws UserException {

        CommonResponse<List<Vwallet>> response = new CommonResponse<>();
        List<Vwallet> list = walletDao.getCustomerWallet(cif);
        if (!list.isEmpty()) {
            response.setData(list);
        } else {
            throw new UserException("44", "Wallet Nopt Found");
        }
        return response;
    }

    @PostMapping(value = WALLET_ACCOUNT + "/{cif}")
    public CommonResponse<WalletAccount> addAccount(@Valid @RequestBody WalletAccount wallet,
                                                    @PathVariable(value = "cif") String cif) throws UserException {

        CommonResponse<WalletAccount> response = new CommonResponse<>();
        if (ValueHelper.isNumeric(String.valueOf(wallet.getAccountNumber()))) {
            if (accountDao.getAccountNumber(wallet.getAccountNumber(), cif) != null) {
                if (walletDao.checkWalletAccount(wallet.getAccountNumber()) == null) {
                    if (walletDao.addAccount(wallet)) {
                        response.setData(wallet);
                    } else {
                        throw new UserException("43", "Bad request");
                    }
                } else {
                    throw new UserException("43", "Account Number already used in the same wallet");
                }
            } else {
                throw new UserException("43", "Account is not valid");
            }

        } else {
            throw new UserException("43", "Account should be number");
        }
        return response;
    }

    @PostMapping(value = WALLET_ACN)
    public CommonResponse<Wallet> newWallet(@Valid @RequestBody Wallet wallet,
                                            @PathVariable(value = "acn") Long acn,
                                            @PathVariable(value = "cif") String cif) throws UserException {

        CommonResponse<Wallet> response = new CommonResponse<>();
        Customer customer = customerDao.getCustomer(cif);
        Long account = accountDao.checkAccount(cif, acn);
        if (customer != null && account != null) {

            if (walletDao.isUsedCashtag(wallet.getCashTag())) {
                throw new UserException("43", "Cash Tag already used");
            }

            wallet.setFullName(customer.getFname() + " " + customer.getLname());
            wallet.setPlaceOb(customer.getPlaceOb());
            wallet.setBirthDate(customer.getBrithDate());
            wallet.setPhoneNumber(customer.getPhoneNumber());
            wallet.setActiveBallance(Long.valueOf(0));

            if (walletDao.createWallet(wallet, acn)) {
                response.setData(wallet);
            } else {
                throw new UserException("43", "Failed");
            }

        } else {
            throw new UserException("43", "Account Number Not Found");
        }
        return response;
    }

    @PostMapping(value = WALLET_UNREG)
    public CommonResponse<Wallet> unregWallet(@PathVariable(value = "wid") Integer wid) {

        CommonResponse<Wallet> response = new CommonResponse<>();

        try {
            if (walletDao.unregWallet(wid)) {
                Wallet wallet = new Wallet();
                wallet.setWalletId(wid);
                response.setData(wallet);
            } else {
                throw new UserException("43", "Bad request");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @PutMapping(value = "/wallet/update-password")
    public CommonResponse updatePassword(@RequestBody Wallet wallet) throws UserException {

        CommonResponse response = new CommonResponse<>();

        if (walletDao.updatePassword(wallet.getWalletId(), wallet.getPassword())) {
            return response;
        } else {
            throw new UserException("43", "Update Failed");
        }
    }

    @PutMapping(value = "/wallet/update-wallet-name")
    public CommonResponse updateWalletName(@RequestBody Wallet wallet) throws UserException {

        CommonResponse response = new CommonResponse<>();

        if (walletDao.updateWalletNAme(wallet.getWalletId(), wallet.getWalletName())) {
            return response;
        } else {
            throw new UserException("43", "Update Failed");
        }
    }

}
