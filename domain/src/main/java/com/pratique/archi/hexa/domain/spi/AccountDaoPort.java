package com.pratique.archi.hexa.domain.spi;

import com.pratique.archi.hexa.domain.exception.MaxDepositPerDayException;
import com.pratique.archi.hexa.domain.model.AccountTransactionDto;

public interface AccountDaoPort {

	void deposit(AccountTransactionDto accountTransaction) throws MaxDepositPerDayException;

}
