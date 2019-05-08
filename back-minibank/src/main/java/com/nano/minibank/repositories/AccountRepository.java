package com.nano.minibank.repositories;

import com.nano.minibank.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    // get profile
    @Query("FROM Account WHERE accountNumber= :acn AND customerNumber= :cif")
    Account getProfile(@Param("acn") Long acn, @Param("cif") String cif);

    // used to generate sequel account
    @Query("SELECT COUNT(accountNumber)+1 FROM Account")
    int nextRow();

//    // get last balance
    @Query("SELECT ballance FROM Account WHERE accountNumber= :acn")
    Long getBallance(@Param("acn") Long acn);

    // update password account
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Account SET password= :pass WHERE accountNumber= :acn AND customerNumber= :cif")
    int updatePassword(@Param("pass") String pass,
                       @Param("acn") Long acn,
                       @Param("cif") String cif);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Account SET ballance= :blc WHERE accountNumber= :acn")
    int updateBallance(@Param("blc") Double blc,
                       @Param("acn") Long acn);

    Account findByAccountNumberAndCustomerNumber(Long acn, String cif);

    @Query("SELECT accountNumber FROM Account WHERE accountNumber= :acn AND customerNumber= :cif ")
    Long getAccountNumber(@Param("acn") Long acn,
                          @Param("cif") String cif);
}

