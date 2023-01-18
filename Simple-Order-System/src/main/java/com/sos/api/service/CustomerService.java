package com.sos.api.service;

import com.sos.api.entity.*;
import com.sos.api.repository.CustomerRepository;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Component
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    Gson gson = new Gson();

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer getCustomer(long id) {
        return customerRepository.findByIdAndActive(id, true);
    }

    public Customer getCustomer(String id) {
        return customerRepository.findByIdAndActive(Long.parseLong(id), true);
    }

    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    public Customer createCustomer(Customer customer){
        Customer savedCustomer = customerRepository.save(customer);
        return savedCustomer;
    }

    public Customer updateCustomer(Customer customer) {
        return save(customer);
    }

    public Boolean deleteCustomer(Map<String, Long> customerMap) {
        long customerId = customerMap.get("customerId");
        return deleteCustomer(customerId);
    }

    public Boolean deleteCustomer(long customerId){
        Customer customer = getCustomer(customerId);
        return deleteCustomer(customer);
    }

    public Boolean deleteCustomer(Customer customer){
        customer.setActive(false);
        return Objects.equals(save(customer), customer);
    }

    public Customer save (Customer customer) {
        try {
            return customerRepository.save(customer);
        } catch (IllegalArgumentException e) {
            log.error("Create new customer", e);
        }
        return null;
    }

}
