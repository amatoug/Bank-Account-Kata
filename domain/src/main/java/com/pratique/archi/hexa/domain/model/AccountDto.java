package com.pratique.archi.hexa.domain.model;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Data;

@Data
@JsonRootName(value = "Account")
@JsonInclude(value = Include.NON_NULL)
public class AccountDto {
    private String accountNumber;

    private BigDecimal balance;
    
    List<AccountTransactionDto> transactions;

}
