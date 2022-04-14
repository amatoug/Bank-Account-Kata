package com.pratique.archi.hexa.service.adapter.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.pratique.archi.hexa.domain.exception.BusinessException;
import com.pratique.archi.hexa.domain.model.AccountDto;
import com.pratique.archi.hexa.domain.model.AccountTransactionDto;

public interface AccountController {

	@PostMapping("/account/add")
	ResponseEntity<Void> addAccount(@RequestBody AccountDto account);

	@PostMapping("/account/deposit")
	ResponseEntity<Void> deposit(@RequestBody AccountTransactionDto accountTransaction) throws BusinessException;

	@PostMapping("/account/withDrawal")
	ResponseEntity<Void> makeWithDrawal(@RequestBody AccountTransactionDto accountTransaction) throws BusinessException;

	@GetMapping("/account")
	ResponseEntity<List<AccountDto>> getAccounts();

	@GetMapping("/account/{accountNumber}")
	ResponseEntity<List<AccountTransactionDto>> getAccountHistory(@PathVariable String accountNumber,
			@RequestParam(value = "fromDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fromDate,
			@RequestParam(value = "toDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime toDate);

}
