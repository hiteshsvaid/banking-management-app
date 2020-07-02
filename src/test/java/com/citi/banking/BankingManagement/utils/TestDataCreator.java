package com.citi.banking.BankingManagement.utils;

import com.citi.banking.BankingManagement.entities.Account;
import com.citi.banking.BankingManagement.entities.AccountType;
import com.citi.banking.BankingManagement.entities.Customer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TestDataCreator {
    public static List<Account> createMockAccounts() {
        List<Account> mockAccounts = new ArrayList<>();
        Account mockAccount = createMockAccount();
        mockAccounts.add(mockAccount);
        return mockAccounts;
    }

    public static Account createMockAccount() {
        Account account = new Account();
        account.setType(AccountType.CHECKING);
        account.setAmount(new BigDecimal(200));
        return account;
    }

    public static List<Customer> createMockCustomers() {
        List<Customer> mockCustomers = new ArrayList<>();
        Customer mockCustomer = createMockCustomer(1L);
        mockCustomers.add(mockCustomer);
        return mockCustomers;
    }

    public static Customer createMockCustomer(Long id) {
        Customer customer = new Customer();
        customer.setId(id);
        customer.setFirstName("Smith");
        customer.setFirstName("Thomson");
        return customer;
    }
}
