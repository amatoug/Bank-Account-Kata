package com.pratique.archi.hexa.domain.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Data;

@Data
@JsonRootName(value = "Account")
public class AccountDto {
    private String accountNumber;

    private BigDecimal balance;

}
