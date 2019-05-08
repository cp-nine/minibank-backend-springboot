package com.nano.minibank.repositories;

import com.nano.minibank.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface CustomerRepository extends JpaRepository<Customer, String> {

    // when create new account
    @Query("SELECT CASE WHEN COUNT(customerNumber) > 0 THEN true ELSE false END " +
            "FROM Customer WHERE customerNumber= :cif")
    boolean isCustomer(@Param("cif") String cif);

    // check availability customer
    Customer findByUsername(String username);

    // update password
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Customer SET password= :pass WHERE customerNumber= :cif")
    int updatePassword(@Param("pass") String pass, @Param("cif") String cif);

    // used to generate sequel account
    @Query("SELECT COUNT(customerNumber)+1 FROM Customer")
    int nextRow();

    // login
    Customer findByUsernameAndPassword(String username, String password);

}
