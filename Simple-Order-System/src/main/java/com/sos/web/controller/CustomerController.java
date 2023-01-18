package com.sos.web.controller;


import com.sos.api.entity.Customer;
import com.sos.api.entity.CustomerOrder;
import com.sos.api.service.CustomerService;
import com.sos.web.links.CustomerLinks;
import com.sos.web.links.CustomerOrderLinks;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Slf4j
@RestController
@RequestMapping(CustomerLinks.CUSTOMER)
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Value("${admin.key}")
    private String ADMIN_KEY;


    @GetMapping(path = CustomerLinks.GET_BY_ID)
    public Customer getCustomer(@PathVariable String id) {
        log.info("Get Customer");
        return customerService.getCustomer(id);
    }

    @GetMapping(path = CustomerLinks.LIST)
    public List<Customer> getAllCustomer(@RequestBody Map<String, String> confidentialMap) {
        log.info("Get Order");
        try{
            if(confidentialMap.get("key").equals(ADMIN_KEY)){
                return customerService.getAllCustomer();
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
    public Customer createCustomer(@RequestBody Customer customer) {
        log.info("Create Customer");
        try {
            return customerService.createCustomer(customer);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PutMapping
    public Boolean updateCustomer(@RequestBody Customer customer){
        log.info("Update Customer");
        try {
            return customerService.updateCustomer(customer) != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @DeleteMapping
    public Boolean deleteCustomer(@RequestBody Map<String, Long> customerMap) {
        log.info("Delete Customer");
        try {
            return customerService.deleteCustomer(customerMap);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
