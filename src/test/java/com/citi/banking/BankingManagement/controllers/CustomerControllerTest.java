package com.citi.banking.BankingManagement.controllers;

import com.citi.banking.BankingManagement.services.AccountService;
import com.citi.banking.BankingManagement.services.CustomerService;
import com.citi.banking.BankingManagement.utils.TestDataCreator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

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
}
