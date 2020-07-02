package com.citi.banking.BankingManagement.controllers;

import com.citi.banking.BankingManagement.domain.Account;
import com.citi.banking.BankingManagement.domain.AccountType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/accounts")
class AccountsController {
    Logger log = LoggerFactory.getLogger(AccountsController.class);

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody List<Account> retrieveAccounts() {
        log.info("Received request: {}");
        return new ArrayList<Account>();
    }

    @GetMapping(path = {"/{accountId}"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody Account retrieveSpecificAccount(Long accountId) {
        log.info("Received request: {}", accountId);
        return new Account(AccountType.CHECKING, new BigDecimal(200));
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public Long createNewAccount(Account account) {
        log.info("Received request: {}", account);
        return new Long(1);
    }

    @PutMapping(
            path = {"/{accountId}"},
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public void updateAccount(String accountId, Account account) {
        log.info("Received update request for id: {} with content {}", accountId, account);
    }

    @DeleteMapping(path = {"/{accountId}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteSpecificAccounts(Long accountId) {
        log.info("Received request: {}", accountId);
    }
}
