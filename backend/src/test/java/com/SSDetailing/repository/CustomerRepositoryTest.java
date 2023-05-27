package com.SSDetailing.repository;

import com.SSDetailing.entity.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
        Customer customer = Customer.builder()
                .firstName("Eduardo")
                .lastName("Montoya")
                .email("test@gmail.com")
                .address("7310 S 78th St")
                .build();

        entityManager.persist(customer);
    }

    @Test
    public void whenFindById_thenReturnCustomer() {
        Customer customer = customerRepository.findById(1L).get();

        assertEquals(customer.getLastName(), "Montoya");
    }
}