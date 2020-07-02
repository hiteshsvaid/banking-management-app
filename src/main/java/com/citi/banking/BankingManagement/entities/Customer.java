package com.citi.banking.BankingManagement.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String socialSecurityNumber;

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private Set<Account> accounts;
}
