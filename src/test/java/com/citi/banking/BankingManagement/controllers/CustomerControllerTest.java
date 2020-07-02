package com.citi.banking.BankingManagement.controllers;

import com.citi.banking.BankingManagement.entities.Account;
import com.citi.banking.BankingManagement.entities.AccountType;
import com.citi.banking.BankingManagement.services.AccountService;
import com.citi.banking.BankingManagement.services.CustomerService;
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
@WebFluxTest(CustomerController.class)
public class CustomerControllerTest {
    @MockBean
    private CustomerService customerService;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void returnStatusOkForRetrieveAccount() {
        Mockito.when(customerService.retrieveCustomers()).thenReturn(TestDataCreator.createMockCustomers());

        webTestClient.get()
                .uri("/customers")
                .exchange()
                .expectStatus().isOk();

        Mockito.verify(customerService, times(1)).retrieveCustomers();
    }

    @Test
    public void returnStatusOkForRetrieveSpecificCustomer() {
        Mockito.when(customerService.retrieveAccountsForCustomer(5L)).thenReturn(TestDataCreator.createMockAccounts());

        webTestClient.get()
                .uri("/customers/5/accounts")
                .exchange()
                .expectStatus().isOk();
        Mockito.verify(customerService, times(1)).retrieveAccountsForCustomer(5L);
    }

    @Test
    public void returnStatusOkForDeleteAccountForCustomer() {
        webTestClient.delete()
                .uri("/customers/5/accounts/1")
                .exchange()
                .expectStatus().isOk();
        Mockito.verify(customerService, times(1))
                .deleteCustomerAccount(5L, 1L);
    }

    @Test
    public void returnStatusOkForCreateNewAccountForCustomer() {
        Account account = TestDataCreator.createMockAccount();
        webTestClient.post()
                .uri("/customers/4/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(account))
                .exchange()
                .expectStatus().isCreated();
        Mockito.verify(customerService, times(1)).createCustomerAccount(4L, account);
    }
}
