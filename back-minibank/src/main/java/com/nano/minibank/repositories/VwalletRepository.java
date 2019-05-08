package com.nano.minibank.repositories;

import com.nano.minibank.entities.Vwallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VwalletRepository extends JpaRepository<Vwallet, Integer> {

    List<Vwallet> findByCustomerNumber(String cif);

    @Query("FROM Vwallet WHERE customerNumber= :cif GROUP BY walletId")
    List<Vwallet> getActiveWallet(@Param("cif") String cif);
}
