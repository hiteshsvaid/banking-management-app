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
        return new Account(AccountType.SAVINGS, new BigDecimal(100));
    }

    public static List<Customer> createMockCustomers() {
        List<Customer> mockCustomers = new ArrayList<>();
        Customer mockCustomer = createMockCustomer();
        mockCustomers.add(mockCustomer);
        return mockCustomers;
    }

    private static Customer createMockCustomer() {
        Customer customer = new Customer();
        customer.setFirstName("Smith");
        customer.setFirstName("Thomson");
        return customer;
    }
}
