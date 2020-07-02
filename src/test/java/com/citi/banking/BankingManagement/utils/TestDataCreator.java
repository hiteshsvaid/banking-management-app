package com.citi.banking.BankingManagement.utils;

import com.citi.banking.BankingManagement.entities.Account;
import com.citi.banking.BankingManagement.entities.AccountType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TestDataCreator {
    public static List<Account> createMockAccounts() {
        List<Account> mockAccounts = new ArrayList<>();
        Account mockAccount = new Account(AccountType.SAVINGS, new BigDecimal(100));
        mockAccounts.add(mockAccount);
        return mockAccounts;
    }
}
