package com.citi.banking.BankingManagement.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue
    private Long customerId;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String socialSecurityNumber;

}
