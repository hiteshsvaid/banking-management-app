package com.citi.banking.BankingManagement.controllers;

import com.citi.banking.BankingManagement.domain.Account;
import com.citi.banking.BankingManagement.domain.AccountType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.math.BigDecimal;

@ExtendWith(SpringExtension.class)
@WebFluxTest(AccountsController.class)
public class AccountsControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void returnStatusOkForRetrieveAccount() {
        webTestClient.get()
                .uri("/accounts")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void returnStatusOkForRetrieveSpecificAccount() {
        webTestClient.get()
                .uri("/accounts/1")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void returnStatusOkForCreateNewAccount() {
        Account account = new Account(AccountType.CHECKING, new BigDecimal(100));
        webTestClient.post()
                .uri("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(account))
                .exchange()
                .expectStatus().isCreated();
    }

    @Test
    public void returnStatusOkForUpdateAccount() {
        Account account = new Account(AccountType.CHECKING, new BigDecimal(100));
        webTestClient.put()
                .uri("/accounts/1")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(account))
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void returnStatusOkForDeleteAccount() {
        webTestClient.delete()
                .uri("/accounts/2")
                .exchange()
                .expectStatus().isOk();
    }
}
