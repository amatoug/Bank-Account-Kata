package com.pratique.archi.hexa.service.adapter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.pratique.archi.hexa.domain.exception.MaxDepositPerDayException;
import com.pratique.archi.hexa.domain.model.AccountTransactionDto;
import com.pratique.archi.hexa.service.api.AccountService;

@RestController
public class AccountControllerImpl implements AccountController {

    private AccountService accountService;

    @Autowired
    public AccountControllerImpl(AccountService accountService) {
        this.accountService = accountService;
    }

	public ResponseEntity<Void> deposit(AccountTransactionDto accountTransaction) throws MaxDepositPerDayException  {
		accountService.deposit(accountTransaction);
        return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
