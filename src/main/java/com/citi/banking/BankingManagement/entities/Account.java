package com.citi.banking.BankingManagement.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
public class Account {
    @Id
    @GeneratedValue
    private Long accountId;
    private AccountType type;
    private BigDecimal amount;

    public Account(AccountType type, BigDecimal amount) {
        this.type = type;
        this.amount = amount;
    }
}
