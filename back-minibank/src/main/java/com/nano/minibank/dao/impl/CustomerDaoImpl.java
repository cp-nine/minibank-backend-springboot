package com.nano.minibank.dao.impl;

import com.nano.minibank.dao.CustomerDao;
import com.nano.minibank.entities.Customer;
import com.nano.minibank.helpers.CodeHelper;
import com.nano.minibank.helpers.ValueHelper;
import com.nano.minibank.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CustomerDaoImpl implements CustomerDao {

    @Autowired
    private CustomerRepository repository;

    @Override
    public boolean newCustomer(Customer customer) {

        try {
            customer.setCustomerNumber(getCif(customer.getFname()));
            repository.save(customer);
            return true;
        } catch (Exception e){
//            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean isUsedUsername(String username){
        try {
            Customer customer = repository.findByUsername(username);
            if (customer != null)
                return true;
            else
                return false;

        } catch (Exception e){
            return false;
        }
    }

    @Override
    public List<Customer> getAllCustomers() {
        return repository.findAll();
    }

    @Override
    public Customer getCustomer(String cif) {
        return repository.findById(cif).orElse(null);
    }

    @Override
    public boolean updatePassword(String password, String cif) {
        try {
            int update = repository.updatePassword(password, cif);
            if (update > 0)
                return true;
            else
                return false;
        } catch (Exception e){
//            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean isCustomer(String cif){

        if (repository.isCustomer(cif))
            return true;
        else
            return false;

    }


    @Override
    public String login(String username, String password){

        Customer customer = repository.findByUsernameAndPassword(username,password);
        if (customer != null){
            return customer.getCustomerNumber();
        } else {
            return "";
        }

    }

    public String getCif(String fname){

        int last = repository.nextRow();
        int val1 = ValueHelper.getRandomNumberInRange(1000,9000);
        int val2 = ValueHelper.getRandomNumberInRange(1000,9000);
        String lastIndex = String.valueOf(val1+""+val2);

        lastIndex = lastIndex+last;

        return CodeHelper.makeCode(fname.substring(0,1).toUpperCase(),lastIndex,8);

    }

    @Override
    public boolean updateCustomer(Customer customer, String cif){

        Customer newData = setData(customer, cif);

        if (newData != null){
            repository.save(newData);
            return true;
        } else {
            return false;
        }
    }

    public Customer setData(Customer customer, String cif){
        Customer current = repository.findById(cif).orElse(null);
        if (current != null){
            customer.setCustomerNumber(current.getCustomerNumber());
            customer.setFname((customer.getFname()!=null)? customer.getFname(): current.getFname());
            customer.setLname((customer.getLname()!=null)? customer.getLname(): current.getLname());
            customer.setPlaceOb((customer.getPlaceOb()!=null)? customer.getPlaceOb(): current.getPlaceOb());
            customer.setBrithDate((customer.getBrithDate()!=null)? customer.getBrithDate(): current.getBrithDate());
            customer.setGender((customer.getGender()!=null)? customer.getGender(): current.getGender());
            customer.setJob((customer.getJob()!=null)? customer.getJob(): current.getJob());
            customer.setAddress((customer.getAddress()!=null)? customer.getAddress(): current.getAddress());
            customer.setRangeSalary((customer.getRangeSalary()!=null)? customer.getRangeSalary(): current.getRangeSalary());
            customer.setEmail((customer.getEmail()!=null)? customer.getEmail(): current.getEmail());
            customer.setUsername(current.getUsername());
            customer.setPassword(current.getPassword());

            return customer;
        } else {
            return null;
        }
    }

}
