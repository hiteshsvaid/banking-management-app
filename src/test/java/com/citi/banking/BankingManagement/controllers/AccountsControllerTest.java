package com.citi.banking.BankingManagement.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

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
}
