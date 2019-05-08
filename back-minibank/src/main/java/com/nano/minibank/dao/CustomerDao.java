package com.nano.minibank.dao;

import com.nano.minibank.entities.Customer;

import java.util.List;

public interface CustomerDao {
    boolean newCustomer(Customer customer);

    boolean isUsedUsername(String username);

    List<Customer> getAllCustomers();

    Customer getCustomer(String cif);

    boolean updatePassword(String password, String cif);

    boolean isCustomer(String cif);

    String login(String username, String password);

    boolean updateCustomer(Customer customer, String cif);
}
