package com.nano.minibank.beans;

import com.nano.minibank.dao.AccountDao;
import com.nano.minibank.dao.impl.AccountDaoImpl;
import com.nano.minibank.entities.Account;
import org.springframework.context.annotation.Bean;

public class AccountBean {

    @Bean
    public AccountDao accountDao(){
        return new AccountDaoImpl();
    }

    @Bean
    public Account account(){
        return new Account();
    }

}
