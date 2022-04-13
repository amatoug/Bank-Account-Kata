package com.pratique.archi.hexa.domain.spi;

import java.util.List;

import com.pratique.archi.hexa.domain.exception.BusinessException;
import com.pratique.archi.hexa.domain.model.AccountDto;
import com.pratique.archi.hexa.domain.model.AccountTransactionDto;

public interface AccountDaoPort {

	void deposit(AccountTransactionDto accountTransaction) throws BusinessException;

	void addAccount(AccountDto account);

	List<AccountDto> getAccounts();

	void makeWithDrawal(AccountTransactionDto accountTransaction) throws BusinessException;

}
