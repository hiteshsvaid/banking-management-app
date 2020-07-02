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
    public void retrieveCustomersTest() {
        when(customerRepository.findAll()).thenReturn(TestDataCreator.createMockCustomers());
        List<Customer> customers = customerService.retrieveCustomers();
        Mockito.verify(customerRepository, times(1)).findAll();
        assertEquals(1, customers.size());
    }

    @Test
    public void retrieveAccountsForCustomerTest() {
        Customer testCustomer = new Customer();
        testCustomer.setId(10L);
        when(accountRepository.findByCustomer(testCustomer))
                .thenReturn(TestDataCreator.createMockAccounts());
        List<Account> accounts = customerService.retrieveAccountsForCustomer(10L);
        Mockito.verify(accountRepository, times(1)).findByCustomer(testCustomer);
        assertEquals(1, accounts.size());
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
        verifyAccount.setCustomer(customer);
        Mockito.verify(accountRepository, times(1)).save(verifyAccount);
    }
}
