package com.citi.banking.BankingManagement.controllers;

import com.citi.banking.BankingManagement.dto.AccountTransferRequest;
import com.citi.banking.BankingManagement.entities.Account;
import com.citi.banking.BankingManagement.services.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.math.BigDecimal;

import static org.mockito.Mockito.times;

@ExtendWith(SpringExtension.class)
@WebFluxTest(TransferController.class)
public class TransferControllerTest {

    @MockBean
    private AccountService accountService;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void returnStatusOkForTransferFund() {
        Account fromAccount = new Account();
        fromAccount.setAmount(new BigDecimal(400));
        fromAccount.setId(1L);

        Account toAccount = new Account();
        toAccount.setAmount(new BigDecimal(200));
        toAccount.setId(3L);

        AccountTransferRequest accountTransferRequest = new AccountTransferRequest();
        accountTransferRequest.setFromAccountId(fromAccount.getId());
        accountTransferRequest.setToAccountId(toAccount.getId());
        accountTransferRequest.setTransferAmount(new BigDecimal(100));

        webTestClient.post()
                .uri("/transfer")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(accountTransferRequest))
                .exchange()
                .expectStatus().isOk();

        Mockito
                .verify(accountService, times(1))
                .transferFunds(1L, 3L, new BigDecimal(100));
    }
}
