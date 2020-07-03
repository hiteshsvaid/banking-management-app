package com.citi.banking.BankingManagement.controllers;

import com.citi.banking.BankingManagement.dto.AccountTransferRequest;
import com.citi.banking.BankingManagement.dto.AccountTransferResponse;
import com.citi.banking.BankingManagement.services.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transfer")
@Slf4j
public class TransferController {

    private final AccountService accountService;

    TransferController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {
            MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    AccountTransferResponse transfer(@RequestBody AccountTransferRequest accountTransferRequest) {
        log.info("Received request: {}", accountTransferRequest);
        this.accountService.transferFunds(accountTransferRequest.getFromAccountId(), accountTransferRequest.getToAccountId(), accountTransferRequest.getTransferAmount());
        return new AccountTransferResponse();
    }
}
