package com.citi.banking.BankingManagement.controllers;

import com.citi.banking.BankingManagement.entities.Account;
import com.citi.banking.BankingManagement.exceptions.AccountNotFoundException;
import com.citi.banking.BankingManagement.services.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@Slf4j
class AccountsController {
    private final AccountService accountService;

    AccountsController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody List<Account> retrieveAccounts() {
        log.info("Received request");
        return accountService.retrieveAccounts();
    }

    @GetMapping(path = {"/{accountId}"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody Account retrieveSpecificAccount(@PathVariable(value = "accountId") Long accountId) {
        log.info("RetrieveSpecificAccount for ID: {}", accountId);
        return accountService.retrieveAccount(accountId).orElseThrow(() -> new AccountNotFoundException(accountId));
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public Account createNewAccount(@RequestBody Account account) {
        log.info("CreateNewAccount for request: {}", account);
        return accountService.createAccount(account);
    }

    @PutMapping(
            path = {"/{accountId}"},
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public void updateAccount(@PathVariable(value = "accountId") Long accountId, @RequestBody Account account) {
        log.info("Received update request for id: {} with content {}", accountId, account);
        accountService.updateAccount(accountId, account);
    }

    @DeleteMapping(path = {"/{accountId}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteSpecificAccounts(@PathVariable(value = "accountId") Long accountId) {
        log.info("DeleteSpecificAccounts for ID: {}", accountId);
        accountService.deleteAccount(accountId);
    }
}
