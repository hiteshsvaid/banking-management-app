package com.citi.banking.BankingManagement.services;

import com.citi.banking.BankingManagement.entities.Account;
import com.citi.banking.BankingManagement.entities.Customer;
import com.citi.banking.BankingManagement.repositories.AccountRepository;
import com.citi.banking.BankingManagement.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;

    public CustomerService(CustomerRepository customerRepository, AccountRepository accountRepository) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
    }

    public List<Customer> retrieveCustomers() {
        return this.customerRepository.findAll();
    }

    public List<Account> retrieveAccountsForCustomer(Long customerId) {
        Customer customer = new Customer();
        customer.setId(customerId);
        return this.accountRepository.findByCustomer(customer);
    }

}