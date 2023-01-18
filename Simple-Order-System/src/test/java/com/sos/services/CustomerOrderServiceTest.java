package com.sos.services;

import com.sos.api.repository.CustomerOrderRepository;
import com.sos.api.service.CustomerOrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CustomerOrderServiceTest {

    @Mock
    private CustomerOrderRepository customerOrderRepository;


    private CustomerOrderService customerOrderService;

    @BeforeEach
    void setUp() {
        this.customerOrderService = new CustomerOrderService(this.customerOrderRepository);
    }

    @Test
    void getAllCustomerOrder() {
        customerOrderService.getAllCustomerOrder();
        verify(customerOrderRepository).findAll();
    }
}