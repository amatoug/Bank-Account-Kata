package com.pratique.archi.hexa.service.adapter;

import org.springframework.beans.factory.annotation.Autowired;

import com.pratique.archi.hexa.domain.exception.MaxDepositPerDayException;
import com.pratique.archi.hexa.domain.model.AccountTransactionDto;
import com.pratique.archi.hexa.domain.spi.AccountDaoPort;
import com.pratique.archi.hexa.service.api.AccountService;

public class AccountServiceAdapter implements AccountService {
	private AccountDaoPort accountPersistencePort;

	@Autowired
	public AccountServiceAdapter(AccountDaoPort accountPersistencePort) {
		this.accountPersistencePort = accountPersistencePort;
	}

	public void deposit(AccountTransactionDto accountTransaction) throws MaxDepositPerDayException {
		accountPersistencePort.deposit(accountTransaction);
	}

}
