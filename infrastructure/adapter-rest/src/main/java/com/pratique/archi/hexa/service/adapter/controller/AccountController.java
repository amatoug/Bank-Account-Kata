package com.pratique.archi.hexa.service.adapter.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.pratique.archi.hexa.domain.exception.MaxDepositPerDayException;
import com.pratique.archi.hexa.domain.model.AccountTransactionDto;

public interface AccountController {

  
    @PostMapping("/account")
    ResponseEntity<Void> deposit(@RequestBody AccountTransactionDto accountTransaction) throws MaxDepositPerDayException;
}
