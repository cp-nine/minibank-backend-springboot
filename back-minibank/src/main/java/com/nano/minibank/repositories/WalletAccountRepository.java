package com.nano.minibank.repositories;

import com.nano.minibank.entities.WalletAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WalletAccountRepository extends JpaRepository<WalletAccount, Integer> {

    List<WalletAccount> findByWalletId(Integer walletId);
    WalletAccount findByAccountNumber(Long acn);


}
