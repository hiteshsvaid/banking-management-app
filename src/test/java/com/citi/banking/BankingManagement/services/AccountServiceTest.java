package com.citi.banking.BankingManagement.services;

import com.citi.banking.BankingManagement.entities.Account;
import com.citi.banking.BankingManagement.entities.AccountType;
import com.citi.banking.BankingManagement.repositories.AccountRepository;
import com.citi.banking.BankingManagement.utils.TestDataCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class AccountServiceTest {

    private AccountService accountService;

    @MockBean
    private AccountRepository accountRepository;

    @BeforeEach
    public void before() {
        accountService = new AccountService(accountRepository);
    }

    @Test
    public void retrieveAccountsTest() {
        when(accountRepository.findAll()).thenReturn(TestDataCreator.createMockAccounts());

        List<Account> accounts = accountService.retrieveAccounts();

        Mockito.verify(accountRepository, times(1)).findAll();
        assertEquals(1, accounts.size());
    }
}
