package com.SSDetailing.controller;

import com.SSDetailing.entity.Customer;
import com.SSDetailing.error.CustomerNotFoundException;
import com.SSDetailing.service.CustomerService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    private final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    @GetMapping
    public List<Customer> getCustomers() {
        LOGGER.info("Inside getCustomers of Customer Controller");
        return customerService.getCustomers();
    }

    @PostMapping
    public Customer saveCustomer(@Valid @RequestBody Customer customer) {
        return  customerService.saveCustomer(customer);
    }

    @GetMapping("/{customerId}")
    public Customer getCustomerById(@PathVariable("customerId") Long id) throws CustomerNotFoundException {
        return customerService.getCustomerById(id);
    }

    @DeleteMapping("/{customerId}")
    public String deleteCustomerById(@PathVariable("customerId") Long id) {
        customerService.deleteCustomerById(id);
        return "Customer Deleted";
    }

    @PutMapping("/{customerId}")
    public Customer updateCustomer(@PathVariable("customerId") Long id, @RequestBody Customer customer) {
        return customerService.updateCustomer(id, customer);
    }

    @GetMapping("/name/{lastName}")
    public List<Customer> getCustomerByLastName(@PathVariable("lastName") String lastName) {
        return customerService.getCustomerByLastName(lastName);
    }
}
