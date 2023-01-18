package com.sos.repository;

import com.sos.api.entity.Customer;
import com.sos.api.entity.CustomerOrder;
import com.sos.api.repository.CustomerOrderRepository;
import com.sos.api.repository.CustomerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class CustomerOrderRepositoryTest {


    @Autowired
    private CustomerOrderRepository customerOrderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void isCustomerOrderExitsById() {
        Customer customer = new Customer();
        customer.setName("A");
        customer.setEmail("B");
        Customer newCustomer = customerRepository.save(customer);
        CustomerOrder order = new CustomerOrder();
        order.setCustomerId(newCustomer.getId());
        CustomerOrder customerOrder = customerOrderRepository.save(order);
        CustomerOrder actualResult = customerOrderRepository.findByIdAndActive(customerOrder.getId(),true);
        assertThat(actualResult).isNotNull();
    }

    @AfterEach
    void tearDown() {
        System.out.println("tearing down");
        customerOrderRepository.deleteAll();
    }

    @BeforeEach
    void setUp() {
        System.out.println("setting up");
    }
}