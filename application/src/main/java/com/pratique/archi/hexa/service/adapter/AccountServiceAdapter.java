package com.pratique.archi.hexa.service.adapter;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.pratique.archi.hexa.domain.exception.BusinessException;
import com.pratique.archi.hexa.domain.model.AccountDto;
import com.pratique.archi.hexa.domain.model.AccountTransactionDto;
import com.pratique.archi.hexa.domain.spi.AccountDaoPort;
import com.pratique.archi.hexa.service.api.AccountService;

public class AccountServiceAdapter implements AccountService {
	private AccountDaoPort accountPersistencePort;

	@Autowired
	public AccountServiceAdapter(AccountDaoPort accountPersistencePort) {
		this.accountPersistencePort = accountPersistencePort;
	}

	public void deposit(AccountTransactionDto accountTransaction) throws BusinessException {
		accountPersistencePort.deposit(accountTransaction);
	}

	@Override
	public void addAccount(AccountDto account) {
		accountPersistencePort.addAccount(account);
		
	}

	@Override
	public List<AccountDto> getAccounts() {
        return accountPersistencePort.getAccounts();

	}

	@Override
	public void makeWithDrawal(AccountTransactionDto accountTransaction) throws BusinessException {
		accountPersistencePort.makeWithDrawal(accountTransaction);
		
	}

	@Override
	public List<AccountTransactionDto> getAccountHistoryByAccountNumberAndPeriod(String accountNumber, LocalDateTime fromDate,
			LocalDateTime toDate) {
		return accountPersistencePort.getAccountHistoryByAccountNumberAndPeriod(accountNumber,fromDate,toDate);

	}

}
