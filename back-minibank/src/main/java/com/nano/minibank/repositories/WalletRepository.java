package com.nano.minibank.repositories;

import com.nano.minibank.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface WalletRepository extends JpaRepository<Wallet, Integer> {

    @Query("SELECT COUNT(walletId)+1 FROM Wallet")
    int nextRow();

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Wallet SET flagDelete=1 WHERE walletId= :wid")
    int unreg(@Param("wid") Integer wid);

    Wallet findByCashTagAndPassword(String casgtag, String password);

    Wallet findByCashTag(String ct);

    Wallet findByUsername(String username);

}
