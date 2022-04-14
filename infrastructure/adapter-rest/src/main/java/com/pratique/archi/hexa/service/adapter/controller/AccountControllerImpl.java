package com.pratique.archi.hexa.service.adapter.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.pratique.archi.hexa.domain.exception.BusinessException;
import com.pratique.archi.hexa.domain.model.AccountDto;
import com.pratique.archi.hexa.domain.model.AccountTransactionDto;
import com.pratique.archi.hexa.service.api.AccountService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class AccountControllerImpl implements AccountController {

	private AccountService accountService;

	@Autowired
	public AccountControllerImpl(AccountService accountService) {
		this.accountService = accountService;
	}

	public ResponseEntity<Void> deposit(AccountTransactionDto accountTransaction) throws BusinessException {
		accountService.deposit(accountTransaction);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> addAccount(AccountDto account) {
		accountService.addAccount(account);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<AccountDto>> getAccounts() {
		return new ResponseEntity<List<AccountDto>>(accountService.getAccounts(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> makeWithDrawal(AccountTransactionDto accountTransaction) throws BusinessException {
		accountService.makeWithDrawal(accountTransaction);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<AccountTransactionDto>> getAccountHistory(String accountNumber, LocalDateTime fromDate,
			LocalDateTime toDate) {

		return new ResponseEntity<List<AccountTransactionDto>>(
				accountService.getAccountHistoryByAccountNumberAndPeriod(accountNumber, fromDate, toDate),
				HttpStatus.OK);
	}

}
