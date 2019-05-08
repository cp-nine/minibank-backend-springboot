package com.nano.minibank.controllers;

import com.nano.minibank.dao.CustomerDao;
import com.nano.minibank.entities.Customer;
import com.nano.minibank.helpers.CommonResponse;
import com.nano.minibank.helpers.UserException;
import com.nano.minibank.helpers.ValueHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api-v1")
public class CustomerController {

    private static final String CUSTOMER = "/customer";
    private static final String CUSTOMER_LIST = "/customers";
    private static final String CUSTOMER_BY_ID = "/customer/{cif}";
    private static final String CUSTOMER_PASSWORD = "/customer/reset";

    @Autowired
    private CustomerDao customerDao;

    @GetMapping(path = CUSTOMER_LIST)
    public CommonResponse<List<Customer>> getAllCustomers() throws UserException {

        List<Customer> customers = customerDao.getAllCustomers();
        CommonResponse<List<Customer>> respon = new CommonResponse<>();
        if (!customers.isEmpty()) {
            respon.setData(customers);
        } else {
            throw new UserException("44", "Customer Not Found");
        }
        return respon;

    }

    @GetMapping(path = CUSTOMER_BY_ID)
    public CommonResponse<Customer> getCustomer(@PathVariable(value = "cif") String cif) throws UserException {

        CommonResponse<Customer> respon = new CommonResponse<>();
        if (ValueHelper.isNumeric(cif)) {
            throw new UserException("44", "Customer " + cif + " Not Found");
        } else {
            Customer customer = customerDao.getCustomer(cif);

            if (customer != null) {
                respon.setData(customer);
            } else {
                throw new UserException("44", "Customer " + cif + " Not Found");
            }
        }
        return respon;

    }

    @PostMapping(value = CUSTOMER)
    public CommonResponse<Customer> addCustomer(@Valid @RequestBody Customer customer) throws UserException {

        CommonResponse<Customer> response = new CommonResponse<>();
        if (customerDao.isUsedUsername(customer.getUsername())) {
            throw new UserException("43", "Failed, username is used");
        } else {
            if (customerDao.newCustomer(customer)) {
                response.setData(customer);
                return response;
            } else {

                throw new UserException("43", "Failed, to add customer");
            }
        }

    }

    @PutMapping(value = CUSTOMER_PASSWORD)
    public CommonResponse<Customer> updateResponse(@RequestBody Customer customer) throws UserException {
        CommonResponse<Customer> response = new CommonResponse<>();


        if (customerDao.updatePassword(customer.getPassword(), customer.getCustomerNumber())) {
            return response;
        } else {
            throw new UserException("43", "Update failed");
        }

    }

    @PutMapping(value = "/customer/edit/{cif}")
    public CommonResponse updateCustomer(@RequestBody Customer customer,
                                         @PathVariable(value = "cif") String cif) throws UserException {
        CommonResponse response = new CommonResponse<>();

        if (customerDao.updateCustomer(customer, cif)) {
            return response;
        } else {
            throw new UserException("43", "Update failed");
        }

    }

}
