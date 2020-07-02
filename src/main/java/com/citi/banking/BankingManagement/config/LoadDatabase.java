package com.citi.banking.BankingManagement.config;

import com.citi.banking.BankingManagement.entities.Account;
import com.citi.banking.BankingManagement.entities.AccountType;
import com.citi.banking.BankingManagement.entities.Customer;
import com.citi.banking.BankingManagement.repositories.AccountRepository;
import com.citi.banking.BankingManagement.repositories.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Configuration
@Slf4j
public class LoadDatabase {
    @Bean
    CommandLineRunner initDatabase(CustomerRepository customerRepository, AccountRepository accountRepository) {
        return args -> {

            Customer customer = createCustomer("John", "Doe", "123 Hollow Street", "898099321", "829-292-9998");
            log.info("Preloading " + customerRepository.save(customer));
            accountRepository.save(createAccount(customer, new BigDecimal(3200), AccountType.CHECKING, "My Checking"));
            accountRepository.save(createAccount(customer, new BigDecimal(4500), AccountType.MONEY_MARKET, "Stock Account"));

            Customer customer2 = createCustomer("Smith", "Thomson", "232 Hidden Street", "823099321", "729-292-9965");
            log.info("Preloading " + customerRepository.save(customer2));
            accountRepository.save(createAccount(customer2, new BigDecimal(300), AccountType.SAVINGS, "My Saving"));
        };
    }

    private Account createAccount(Customer customer, BigDecimal amount, AccountType accountType, String nickName) {
        Account account = new Account();
        account.setNickName(nickName);
        account.setType(accountType);
        account.setAmount(amount);
        account.setCustomer(customer);
        return account;
    }

    private Customer createCustomer(String firstName, String lastName, String address, String phoneNumber, String socialSecurityNumber) {
        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setAddress(address);
        customer.setPhoneNumber(phoneNumber);
        customer.setSocialSecurityNumber(socialSecurityNumber);
        return customer;
    }
}
