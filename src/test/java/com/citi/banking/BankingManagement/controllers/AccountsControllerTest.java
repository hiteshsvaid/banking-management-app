package com.citi.banking.BankingManagement.controllers;

import com.citi.banking.BankingManagement.entities.Account;
import com.citi.banking.BankingManagement.entities.AccountType;
import com.citi.banking.BankingManagement.services.AccountService;
import com.citi.banking.BankingManagement.utils.TestDataCreator;
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
import java.util.Optional;

import static org.mockito.Mockito.times;

@ExtendWith(SpringExtension.class)
@WebFluxTest(AccountsController.class)
public class AccountsControllerTest {

    @MockBean
    private AccountService accountService;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void returnStatusOkForRetrieveAccount() {
        Mockito.when(accountService.retrieveAccounts()).thenReturn(TestDataCreator.createMockAccounts());

        webTestClient.get()
                .uri("/accounts")
                .exchange()
                .expectStatus().isOk();

        Mockito.verify(accountService, times(1)).retrieveAccounts();
    }

    @Test
    public void returnStatusOkForRetrieveSpecificAccount() {
        Mockito.when(accountService.retrieveAccount(5L)).thenReturn(Optional.of(TestDataCreator.createMockAccount()));

        webTestClient.get()
                .uri("/accounts/5")
                .exchange()
                .expectStatus().isOk();
        Mockito.verify(accountService, times(1)).retrieveAccount(5L);
    }

    @Test
    public void returnStatusNotFoundForRetrieveSpecificAccount() {
        webTestClient.get()
                .uri("/accounts/6")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    public void returnStatusOkForCreateNewAccount() {
        Account account = new Account();
        account.setType(AccountType.CHECKING);
        account.setAmount(new BigDecimal(200));
        webTestClient.post()
                .uri("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(account))
                .exchange()
                .expectStatus().isCreated();
        Mockito.verify(accountService, times(1)).createAccount(account);
    }

    @Test
    public void returnStatusOkForUpdateAccount() {
        Account account = new Account();
        account.setType(AccountType.CHECKING);
        account.setAmount(new BigDecimal(200));
        webTestClient.put()
                .uri("/accounts/1")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(account))
                .exchange()
                .expectStatus().isOk();
        Mockito.verify(accountService, times(1)).updateAccount(1L, account);
    }

    @Test
    public void returnStatusOkForDeleteAccount() {
        webTestClient.delete()
                .uri("/accounts/2")
                .exchange()
                .expectStatus().isOk();
        Mockito.verify(accountService, times(1)).deleteAccount(2L);
    }
}
