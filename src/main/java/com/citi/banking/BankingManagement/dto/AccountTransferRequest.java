package com.citi.banking.BankingManagement.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class AccountTransferRequest {
    private Long fromAccountId;
    private Long toAccountId;
    private BigDecimal transferAmount;
}
