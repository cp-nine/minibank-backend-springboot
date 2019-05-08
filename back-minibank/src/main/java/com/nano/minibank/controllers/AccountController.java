package com.nano.minibank.controllers;

import com.nano.minibank.dao.AccountDao;
import com.nano.minibank.entities.Account;
import com.nano.minibank.helpers.CommonResponse;
import com.nano.minibank.helpers.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api-v1")
public class AccountController {

    private static final String ACCOUNT = "/account";

    @Autowired
    private AccountDao accountDao;

    @PostMapping(value = ACCOUNT)
    public CommonResponse<Account> createAccount(@RequestBody Account account) throws UserException {

        CommonResponse<Account> response = new CommonResponse<>();
        if (accountDao.addNewAccount(account)) {
            response.setData(account);
            return response;
        } else {
            throw new UserException("40", "Bad Request");
        }

    }

}
