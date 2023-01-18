package com.sos.repository;

import com.sos.api.entity.Customer;
import com.sos.api.repository.CustomerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class CustomerRepositoryTest {


    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void isCustomerExitsById() {
        Customer customer = new Customer();
        customer.setName("A");
        customer.setEmail("B");
        Customer newCustomer = customerRepository.save(customer);
        Customer actualResult = customerRepository.findByIdAndActive(newCustomer.getId(),true);
        assertThat(actualResult).isNotNull();
    }

    @AfterEach
    void tearDown() {
        System.out.println("tearing down");
        customerRepository.deleteAll();
    }

    @BeforeEach
    void setUp() {
        System.out.println("setting up");
    }
}