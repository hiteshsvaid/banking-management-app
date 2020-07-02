package com.citi.banking.BankingManagement.exceptions;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(Long accountId) {
        super("Could not find account " + accountId);
    }
}
