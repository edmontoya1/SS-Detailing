package com.SSDetailing.service;

import com.SSDetailing.entity.Customer;
import com.SSDetailing.error.CustomerNotFoundException;
import com.SSDetailing.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(Long id) throws CustomerNotFoundException {
        Optional<Customer> customer = customerRepository.findById(id);

        if (!customer.isPresent()) {
            throw new CustomerNotFoundException("Customer Not Found");
        }

        return customer.get();
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) {
        Customer customerDB = customerRepository.findById(id).get();

        if (Objects.nonNull(customer.getFirstName()) &&
            !"".equalsIgnoreCase(customer.getFirstName())) {
            customerDB.setFirstName(customer.getFirstName());
        }

        if (Objects.nonNull(customer.getLastName()) &&
                !"".equalsIgnoreCase(customer.getLastName())) {
            customerDB.setLastName(customer.getLastName());
        }

        if (Objects.nonNull(customer.getAddress()) &&
                !"".equalsIgnoreCase(customer.getAddress())) {
            customerDB.setAddress(customer.getAddress());
        }

        if (Objects.nonNull(customer.getEmail()) &&
                !"".equalsIgnoreCase(customer.getEmail())) {
            customerDB.setEmail(customer.getEmail());
        }

        return customerRepository.save(customerDB);
    }

    @Override
    public List<Customer> getCustomerByLastName(String lastName) {
        return customerRepository.findByLastName(lastName);
    }
}
