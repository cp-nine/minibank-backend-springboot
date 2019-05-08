package dao;

import dto.CommonResponse;
import entity.Account;

public interface AccountWsDao {
    CommonResponse<Account> addAccount(Account account);
}
