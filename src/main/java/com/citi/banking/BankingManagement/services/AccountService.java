package com.citi.banking.BankingManagement.services;

import com.citi.banking.BankingManagement.entities.Account;
import com.citi.banking.BankingManagement.exceptions.AccountInSufficientBalanceException;
import com.citi.banking.BankingManagement.exceptions.AccountNotFoundException;
import com.citi.banking.BankingManagement.repositories.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Optional<Account> retrieveAccount(Long accountId) {
        return this.accountRepository.findById(accountId);
    }

    public Account createAccount(Account account) {
        return this.accountRepository.save(account);
    }

    public Account updateAccount(Long accountId, Account newAccount) {
        return accountRepository.findById(accountId)
                .map(account -> {
                    account.setAmount(newAccount.getAmount());
                    account.setType(newAccount.getType());
                    return accountRepository.save(account);
                })
                .orElseGet(() -> {
                    newAccount.setId(accountId);
                    return accountRepository.save(newAccount);
                });
    }

    public void deleteAccount(Long accountId) {
        accountRepository.deleteById(accountId);
    }

    public void transferFunds(Long fromAccount, Long toAccount, BigDecimal transferAmount) {
        this.withDraw(fromAccount, transferAmount);
        this.deposit(toAccount, transferAmount);
    }

    public void withDraw(Long id, BigDecimal transferAmount) {
        Optional<Account> accountOptional = accountRepository.findById(id);
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            if (transferAmount.compareTo(account.getAmount()) == 1) {
                throw new AccountInSufficientBalanceException();
            } else {
                BigDecimal newBalance = account.getAmount().subtract(transferAmount);
                account.setAmount(newBalance);
                accountRepository.save(account);
            }
        } else {
            throw new AccountNotFoundException(id);
        }
    }

    public void deposit(Long id, BigDecimal transferAmount) {
        Optional<Account> accountOptional = accountRepository.findById(id);
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            BigDecimal newBalance = account.getAmount().add(transferAmount);
            account.setAmount(newBalance);
            accountRepository.save(account);
        } else {
            throw new AccountNotFoundException(id);
        }
    }
}
