package com.citi.banking.BankingManagement.validators;

import com.citi.banking.BankingManagement.entities.Account;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("beforeCreateWebsiteUserValidator")
public class AccountValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Account.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Account account = (Account) obj;
        if (checkInputString(account.getNickName())) {
            errors.rejectValue("nickName", "nickName.empty");
        }
    }

    private boolean checkInputString(String input) {
        return (input == null || input.trim().length() == 0);
    }
}
