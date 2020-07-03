package com.citi.banking.BankingManagement.services;

import com.citi.banking.BankingManagement.entities.Account;
import com.citi.banking.BankingManagement.entities.Customer;
import com.citi.banking.BankingManagement.repositories.AccountRepository;
import com.citi.banking.BankingManagement.repositories.CustomerRepository;
import com.citi.banking.BankingManagement.utils.TestDataCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class CustomerServiceTest {
    private CustomerService customerService;

    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private AccountRepository accountRepository;

    @BeforeEach
    public void before() {
        customerService = new CustomerService(customerRepository, accountRepository);
    }

    @Test
    public void deleteCustomerAccountTest() {
        Customer customer = new Customer();
        customer.setId(1L);
        Account account = new Account();
        account.setId(5L);
        account.setCustomer(customer);
        customerService.deleteCustomerAccount(1L, 5L);
        Mockito.verify(accountRepository, times(1)).delete(account);
    }

    @Test
    public void createCustomerAccountTest() {
        Account inputAccount = TestDataCreator.createMockAccount();
        Account responseAccount = customerService.createCustomerAccount(3L, inputAccount);

        Account verifyAccount = TestDataCreator.createMockAccount();
        Customer customer = new Customer();
        customer.setId(3L);
        verifyAccount.setAmount(new BigDecimal(0));
        verifyAccount.setCustomer(customer);
        Mockito.verify(accountRepository, times(1)).save(verifyAccount);
    }
}
