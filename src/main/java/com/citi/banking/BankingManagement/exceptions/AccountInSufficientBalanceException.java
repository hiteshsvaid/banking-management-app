package com.citi.banking.BankingManagement.exceptions;

public class AccountInSufficientBalanceException extends RuntimeException {
    public AccountInSufficientBalanceException() {
        super("Insufficient account balance");
    }
}
