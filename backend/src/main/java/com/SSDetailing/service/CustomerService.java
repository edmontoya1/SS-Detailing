package com.SSDetailing.service;

import com.SSDetailing.entity.Customer;
import com.SSDetailing.error.CustomerNotFoundException;

import java.util.List;

public interface CustomerService {
    Customer saveCustomer(Customer customer);

    List<Customer> getCustomers();

    Customer getCustomerById(Long id) throws CustomerNotFoundException;

    void deleteCustomerById(Long id);

    Customer updateCustomer(Long id, Customer customer);

    List<Customer> getCustomerByLastName(String name);
}
