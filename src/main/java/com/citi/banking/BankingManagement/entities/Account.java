package com.citi.banking.BankingManagement.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Account {
    @Id
    @GeneratedValue
    private Long accountId;
    private AccountType type;
    private BigDecimal amount;

    public Account() {
    }

    public Account(AccountType type, BigDecimal amount) {
        this.type = type;
        this.amount = amount;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(accountId, account.accountId) &&
                type == account.type &&
                amount.equals(account.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, type, amount);
    }
}
