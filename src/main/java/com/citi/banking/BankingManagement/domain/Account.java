package com.citi.banking.BankingManagement.domain;

import java.math.BigDecimal;

public class Account {
    private AccountType type;
    private BigDecimal amount;

    public Account(AccountType type, BigDecimal amount) {
        this.type = type;
        this.amount = amount;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
