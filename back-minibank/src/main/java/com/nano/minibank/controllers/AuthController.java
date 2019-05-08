package com.nano.minibank.controllers;
import com.nano.minibank.dao.CustomerDao;
import com.nano.minibank.dao.WalletDao;
import com.nano.minibank.entities.Customer;
import com.nano.minibank.entities.Wallet;
import com.nano.minibank.helpers.CommonResponse;
import com.nano.minibank.helpers.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api-v1")
public class AuthController {

    private static final String LOGIN_CUSTOMER = "/customer/login";
    private static final String LOGIN_WALLET = "/wallet/login";

    @Autowired
    private CustomerDao customerService;

    @Autowired
    private WalletDao walletService;

    @PostMapping(value = LOGIN_CUSTOMER)
    public CommonResponse<Map<String, Object>> customerLogin(@RequestBody Customer customer) throws UserException {

        String cif = customerService.login(customer.getUsername(), customer.getPassword());
        CommonResponse<Map<String, Object>> response = new CommonResponse<>();
        Map<String, Object> map = new HashMap<>();
        if (!cif.equals("")) {
            map.put("customerNumber", cif);
            response.setData(map);
        } else {
            throw new UserException("43", "Not Authorized");
        }
        return response;
    }

    @PostMapping(value = LOGIN_WALLET)
    public CommonResponse<Map<String, Object>> walletLogin(@RequestBody Wallet wallet) throws UserException {

        Integer walletId = walletService.login(wallet.getCashTag(), wallet.getPassword());
        CommonResponse<Map<String, Object>> response = new CommonResponse<>();
        Map<String, Object> map = new HashMap<>();
        if (walletId != null) {
            map.put("walletId", walletId);
            response.setData(map);
        } else {
            throw new UserException("43", "Not Authorized");
        }
        return response;
    }

}

