package com.sos.api.service;

import com.google.gson.Gson;
import com.sos.api.entity.Customer;
import com.sos.api.entity.CustomerOrder;
import com.sos.api.repository.CustomerOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Component
public class CustomerOrderService {

    @Autowired
    private CustomerOrderRepository customerOrderRepository;


    Gson gson = new Gson();

    public CustomerOrderService(CustomerOrderRepository customerOrderRepository) {
        this.customerOrderRepository = customerOrderRepository;
    }

    public CustomerOrder getOrder(long id) {
        return customerOrderRepository.findByIdAndActive(id, true);
    }

    public CustomerOrder getOrder(String id) {
        return customerOrderRepository.findByIdAndActive(Long.parseLong(id), true);
    }

    public List<CustomerOrder> getAllCustomerOrder() {
        return customerOrderRepository.findAll();
    }

    public CustomerOrder createOrder(CustomerOrder customerOrder){
        CustomerOrder savedCustomerOrder = customerOrderRepository.save(customerOrder);
        return savedCustomerOrder;
    }

    public CustomerOrder updateOrder(CustomerOrder customerOrder) {
        return save(customerOrder);
    }

    public Boolean deleteOrder(Map<String, Long> orderMap) {
        long orderId = orderMap.get("orderId");
        return deleteOrder(orderId);
    }

    public Boolean deleteOrder(long orderId){
        CustomerOrder customerOrder = getOrder(orderId);
        return deleteOrder(customerOrder);
    }

    public Boolean deleteOrder(CustomerOrder customerOrder){
        customerOrder.setActive(false);
        return Objects.equals(save(customerOrder), customerOrder);
    }

    public CustomerOrder save (CustomerOrder customerOrder) {
        try {
            return customerOrderRepository.save(customerOrder);
        } catch (IllegalArgumentException e) {
            log.error("Create new customerOrder", e);
        }
        return null;
    }
}
