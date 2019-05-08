package dao;

import dto.CommonResponse;
import entity.Customer;

import java.util.List;

public interface CustomerWsDao {

    List<Customer> getCustomers();

    Customer getCutomer(String cif);

    CommonResponse addCustomer(Customer customer);

    CommonResponse updatePassword(Customer customer);
}
