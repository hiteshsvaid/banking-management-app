package com.citi.banking.BankingManagement.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountsController {
    Logger log = LoggerFactory.getLogger(AccountsController.class);

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody String retrieveAccounts() {
        log.info("Received request: {}");
        return HttpStatus.OK.toString();
    }
}
