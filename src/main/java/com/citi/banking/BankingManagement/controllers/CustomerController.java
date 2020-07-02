package com.citi.banking.BankingManagement.controllers;

import com.citi.banking.BankingManagement.entities.Account;
import com.citi.banking.BankingManagement.entities.Customer;
import com.citi.banking.BankingManagement.services.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@Slf4j
public class CustomerController {

    private final CustomerService customerService;

    CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody List<Customer> retrieveCustomers() {
        return customerService.retrieveCustomers();
    }

    @GetMapping(path = {"/{customerId}/accounts"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody List<Account> retrieveSpecificAccount(@PathVariable(value = "customerId") Long customerId) {
        log.info("RetrieveSpecificAccount for ID: {}", customerId);
        return customerService.retrieveAccountsForCustomer(customerId);
    }
}