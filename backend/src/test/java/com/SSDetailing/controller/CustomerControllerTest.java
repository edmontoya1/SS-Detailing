package com.SSDetailing.controller;

import com.SSDetailing.entity.Customer;
import com.SSDetailing.error.CustomerNotFoundException;
import com.SSDetailing.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;
    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = Customer.builder()
                .firstName("Eduardo")
                .lastName("Montoya")
                .email("test@gmail.com")
                .address("7310 S 78th St")
                .id(1L)
                .build();
    }

    @Test
    void saveCustomer() throws Exception {
        Customer inputCustomer = Customer.builder()
                .firstName("Eduardo")
                .lastName("Montoya")
                .email("test@gmail.com")
                .address("7310 S 78th St")
                .id(1L)
                .build();

        Mockito.when(customerService.saveCustomer(inputCustomer)).thenReturn(customer);

        mockMvc.perform(post("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"firstName\": \"Eduardo\",\n" +
                        "    \"lastName\": \"Montoya\",\n" +
                        "    \"email\": \"test@gmail.com\",\n" +
                        "    \"address\": \"7310 S 78th St\"\n" +
                        "}")).andExpect(status().isOk());
    }

    @Test
    void getCustomerById() throws Exception {
        Mockito.when(customerService.getCustomerById(1L)).thenReturn(customer);

        mockMvc.perform(get("/customers/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lastName").value(customer.getLastName()));
    }
}