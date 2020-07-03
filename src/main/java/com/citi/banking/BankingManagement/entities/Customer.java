package com.citi.banking.BankingManagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    @JsonIgnore
    private String socialSecurityNumber;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @RestResource(path = "accounts", rel="accounts")
    private List<Account> accounts;
}
