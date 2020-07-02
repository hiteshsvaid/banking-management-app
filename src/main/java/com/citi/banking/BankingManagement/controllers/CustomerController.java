package com.citi.banking.BankingManagement.controllers;

import com.citi.banking.BankingManagement.entities.Account;
import com.citi.banking.BankingManagement.entities.Customer;
import com.citi.banking.BankingManagement.services.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
    public @ResponseBody List<Account> retrieveAccountsForCustomer(@PathVariable(value = "customerId") Long customerId) {
        log.info("RetrieveSpecificAccount for ID: {}", customerId);
        return customerService.retrieveAccountsForCustomer(customerId);
    }

    @DeleteMapping(path = {"/{customerId}/accounts/{accountId}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteAccountForCustomer(
            @PathVariable(value = "customerId") Long customerId,
            @PathVariable(value = "accountId") Long accountId) {
        log.info("DeleteAccount: {} For Customer with ID: {}", customerId, accountId);
        customerService.deleteCustomerAccount(customerId, accountId);
    }

    @PostMapping(path = {"/{customerId}/accounts"}, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public Account createNewCustomerAccount(@PathVariable(value = "customerId") Long customerId, @RequestBody Account account) {
        log.info("CreateNewCustomer with customer id: {} with account details: {}", customerId, account);
        return customerService.createCustomerAccount(customerId, account);
    }
}
