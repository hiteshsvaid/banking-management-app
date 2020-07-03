package com.citi.banking.BankingManagement.controllers.advices;

import com.citi.banking.BankingManagement.exceptions.AccountInSufficientFundException;
import com.citi.banking.BankingManagement.exceptions.AccountNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class AccountInsufficientFundAdvice {
    @ResponseBody
    @ExceptionHandler(AccountInSufficientFundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String accountNotFoundHandler(AccountInSufficientFundException ex) {
        return ex.getMessage();
    }
}
