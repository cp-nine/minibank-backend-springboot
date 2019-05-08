package com.nano.minibank.beans;

import com.nano.minibank.dao.TrxDao;
import com.nano.minibank.dao.impl.TrxDaoImpl;
import org.springframework.context.annotation.Bean;

public class TrxBean {

    @Bean
    public TrxDao trxDao(){
        return new TrxDaoImpl();
    }

}
