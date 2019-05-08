package com.nano.minibank.beans;

import com.nano.minibank.dao.CustomerDao;
import com.nano.minibank.dao.impl.CustomerDaoImpl;
import com.nano.minibank.entities.Customer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerBean {

    @Bean
    public CustomerDao customerDao(){
        return new CustomerDaoImpl();
    }

    @Bean
    public Customer customer(){
        return new Customer();
    }

}
