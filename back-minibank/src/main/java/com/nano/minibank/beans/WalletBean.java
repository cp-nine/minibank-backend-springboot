package com.nano.minibank.beans;

import com.nano.minibank.dao.WalletDao;
import com.nano.minibank.dao.impl.WalletDaoImpl;
import org.springframework.context.annotation.Bean;

public class WalletBean {

    @Bean
    public WalletDao walletDao(){
        return new WalletDaoImpl();
    }

}
