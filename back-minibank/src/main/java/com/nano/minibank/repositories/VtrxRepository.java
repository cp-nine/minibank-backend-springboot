package com.nano.minibank.repositories;

import com.nano.minibank.entities.Vtrx;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VtrxRepository extends JpaRepository<Vtrx, String> {

    @Query("FROM Vtrx WHERE customerNumber= :cif")
    List<Vtrx> getTransaction(@Param("cif") String cif);

    @Query("FROM Vtrx WHERE customerNumber= :cif and (acnDebet= :acn or acnCredit= :acn)")
    List<Vtrx> getTrxWallet(@Param("cif") String cif,
                            @Param("acn") Long acn);

}
