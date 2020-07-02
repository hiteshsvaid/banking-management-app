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
//            customer.setAccounts(accounts);
            log.info("Preloading " + customerRepository.save(customer));

            Account account = new Account();
            account.setType(AccountType.CHECKING);
            account.setAmount(new BigDecimal(200));
            account.setCustomer(customer);
            accountRepository.save(account);
        };
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
