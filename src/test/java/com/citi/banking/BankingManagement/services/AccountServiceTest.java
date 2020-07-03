package com.citi.banking.BankingManagement.services;

import com.citi.banking.BankingManagement.entities.Account;
import com.citi.banking.BankingManagement.entities.AccountType;
import com.citi.banking.BankingManagement.exceptions.AccountInSufficientBalanceException;
import com.citi.banking.BankingManagement.exceptions.AccountNotFoundException;
import com.citi.banking.BankingManagement.repositories.AccountRepository;
import com.citi.banking.BankingManagement.utils.TestDataCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class AccountServiceTest {

    private AccountService accountService;
    private Account fromAccount;
    private Account toAccount;

    @MockBean
    private AccountRepository accountRepository;

    @BeforeEach
    public void before() {
        accountService = new AccountService(accountRepository);
        fromAccount = new Account();
        fromAccount.setId(1L);

        toAccount = new Account();
        toAccount.setId(3L);
    }

    @Test
    public void transferBetweenAccountTestInvalidFromAccount() {
        when(accountRepository.findById(fromAccount.getId())).thenReturn(Optional.empty());

        BigDecimal transferAmount = new BigDecimal(100);

        Assertions.assertThrows(AccountNotFoundException.class, () -> {
            accountService.transferFunds(1L, 3L, transferAmount);
        });
    }

    @Test
    public void transferBetweenAccountTestInvalidToAccount() {
        fromAccount.setAmount(new BigDecimal(100));
        when(accountRepository.findById(fromAccount.getId())).thenReturn(Optional.of(fromAccount));
        when(accountRepository.findById(toAccount.getId())).thenReturn(Optional.empty());

        BigDecimal transferAmount = new BigDecimal(100);

        Assertions.assertThrows(AccountNotFoundException.class, () -> {
            accountService.transferFunds(1L, 3L, transferAmount);
        });
    }

    @Test
    public void transferBetweenAccountTestInsufficientBalanceInToAccount() {
        fromAccount.setAmount(new BigDecimal(100));
        when(accountRepository.findById(fromAccount.getId())).thenReturn(Optional.of(fromAccount));
        when(accountRepository.findById(toAccount.getId())).thenReturn(Optional.of(toAccount));

        BigDecimal transferAmount = new BigDecimal(200);

        Assertions.assertThrows(AccountInSufficientBalanceException.class, () -> {
            accountService.transferFunds(1L, 3L, transferAmount);
        });
    }

    @Test
    public void transferBetweenAccountTestSuccess() {
        fromAccount.setAmount(new BigDecimal(400));
        toAccount.setAmount(new BigDecimal(200));
        when(accountRepository.findById(fromAccount.getId())).thenReturn(Optional.of(fromAccount));
        when(accountRepository.findById(toAccount.getId())).thenReturn(Optional.of(toAccount));

        BigDecimal transferAmount = new BigDecimal(100);

        accountService.transferFunds(fromAccount.getId(), toAccount.getId(), transferAmount);

        Mockito.verify(accountRepository, times(1)).save(fromAccount);
    }

    @Test
    public void retrieveAccountTest() {
        when(accountRepository.findById(3L)).thenReturn(Optional.of(TestDataCreator.createMockAccount()));
        Optional<Account> account = accountService.retrieveAccount(3L);
        Mockito.verify(accountRepository, times(1)).findById(3L);
        assertTrue(account.isPresent());
        assertEquals(AccountType.CHECKING, account.get().getType());
    }

    @Test
    public void updateAccountTest() {
        when(accountRepository.findById(6L)).thenReturn(Optional.of(TestDataCreator.createMockAccount()));
        Account account = accountService.updateAccount(6L, TestDataCreator.createMockAccount());
        Mockito.verify(accountRepository, times(1)).findById(6L);
    }

    @Test
    public void deleteAccountTest() {
        accountService.deleteAccount(9L);
        Mockito.verify(accountRepository, times(1)).deleteById(9L);
    }
}
