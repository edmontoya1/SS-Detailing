package com.SSDetailing.service;

import com.SSDetailing.entity.Customer;
import com.SSDetailing.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @MockBean
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        Customer customer = Customer.builder()
                .firstName("Eduardo")
                .lastName("Montoya")
                .email("test@gmail.com")
                .address("7310 S 78th St")
                .build();

        List<Customer> customerList = List.of(customer);

        Mockito.when(customerRepository.findByLastName("Montoya")).thenReturn(customerList);
    }

    @Test
    @DisplayName("Get Customer By Last Name")
    public void whenValidCustomerName_thenCustomerShouldFound() {
        String customerLastName = "Montoya";
        List<Customer> found = customerService.getCustomerByLastName(customerLastName);

        assertEquals(customerLastName, found.get(0).getLastName());
    }
}