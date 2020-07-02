package com.citi.banking.BankingManagement.services;

import com.citi.banking.BankingManagement.entities.Account;
import com.citi.banking.BankingManagement.repositories.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> retrieveAccounts() {
        return  this.accountRepository.findAll();
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
                    newAccount.setAccountId(accountId);
                    return accountRepository.save(newAccount);
                });
    }

    public void deleteAccount(Long accountId) {
        accountRepository.deleteById(accountId);
    }
}
