package dao;

import entity.Account;
import entity.Customer;

public interface AuthWsDao {

    Customer loginCustomer(Customer customer);

    Account loginAccount(Account account);

}
