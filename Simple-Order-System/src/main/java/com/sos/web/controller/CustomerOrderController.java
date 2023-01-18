package com.sos.web.controller;


import com.sos.api.entity.Customer;
import com.sos.api.entity.CustomerOrder;
import com.sos.api.enums.Category;
import com.sos.api.service.CustomerOrderService;
import com.sos.api.service.CustomerService;
import com.sos.web.links.CustomerOrderLinks;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Slf4j
@RestController
@RequestMapping(CustomerOrderLinks.ORDER)
public class CustomerOrderController {

    @Autowired
    CustomerOrderService customerOrderService;

    @Autowired
    CustomerService customerService;

    @Value("${admin.key}")
    private String ADMIN_KEY;


    @GetMapping(path = CustomerOrderLinks.GET_BY_ID)
    public CustomerOrder getOrder(@PathVariable String id) {
        log.info("Get Order");
        return customerOrderService.getOrder(id);
    }

    @GetMapping(path = CustomerOrderLinks.LIST)
    public List<CustomerOrder> getAllOrder(@RequestBody Map<String, String> confidentialMap) {
        log.info("Get Order");
        try{
            if(confidentialMap.get("key").equals(ADMIN_KEY)){
                return customerOrderService.getAllCustomerOrder();
            }else{
                return null;
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @PostMapping
    public CustomerOrder createOrder(@RequestBody CustomerOrder customerOrder) {
        log.info("Create Order");
        try {

            CustomerOrder newOrder = customerOrderService.createOrder(customerOrder);
            Customer customer = customerService.getCustomer(customerOrder.getCustomerId());

            if(customer.getCategory().equals(Category.gold)){
                newOrder.setDiscount(10.0);
            }else if(customer.getCategory().equals(Category.platinum)){
                newOrder.setDiscount(20.0);
            }

            int ordersCount = customer.getAllOrders().size();
            if(ordersCount>=10 && ordersCount<20 && customer.getCategory().equals(Category.regular)){
                customer.setCategory(Category.gold);
            }else if(ordersCount>=20 && customer.getCategory().equals(Category.gold)){
                customer.setCategory(Category.platinum);
            }

            Set<CustomerOrder> orders = customer.getAllOrders();
            orders.add(newOrder);
            customer.setAllOrders(orders);

            customerService.updateCustomer(customer);

            return  newOrder;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PutMapping
    public Boolean updateOrder(@RequestBody CustomerOrder customerOrder){
        log.info("Update Order");
        try {
            return customerOrderService.updateOrder(customerOrder) != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @DeleteMapping
    public Boolean deleteOrder(@RequestBody Map<String, Long> orderMap) {
        log.info("Delete Order");
        try {
            return customerOrderService.deleteOrder(orderMap);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
