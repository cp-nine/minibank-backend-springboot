package com.nano.minibank.repositories;

import com.nano.minibank.entities.TrxEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrxRepository extends JpaRepository<TrxEntity, Integer> {

}
