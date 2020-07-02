package com.citi.banking.BankingManagement.entities;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
public class Account {
    @Id
    @GeneratedValue
    private Long id;
    private AccountType type;
    private BigDecimal amount;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;


    public Account(AccountType type, BigDecimal amount) {
        this.type = type;
        this.amount = amount;
    }
}
