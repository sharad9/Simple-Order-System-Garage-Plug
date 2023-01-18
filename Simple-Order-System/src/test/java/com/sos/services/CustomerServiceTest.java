package com.sos.services;

import com.sos.api.repository.CustomerRepository;
import com.sos.api.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;


    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        this.customerService = new CustomerService(this.customerRepository);
    }

    @Test
    void getAllCustomer() {
        customerService.getAllCustomer();
        verify(customerRepository).findAll();
    }
}