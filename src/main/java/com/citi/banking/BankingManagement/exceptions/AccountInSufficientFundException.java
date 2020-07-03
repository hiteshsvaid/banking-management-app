package com.citi.banking.BankingManagement.exceptions;

public class AccountInSufficientFundException extends RuntimeException {
    public AccountInSufficientFundException() {
        super("Insufficient account balance");
    }
}
