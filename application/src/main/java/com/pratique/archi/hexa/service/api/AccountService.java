package com.pratique.archi.hexa.service.api;

import java.util.List;

import com.pratique.archi.hexa.domain.exception.BusinessException;
import com.pratique.archi.hexa.domain.model.AccountDto;
import com.pratique.archi.hexa.domain.model.AccountTransactionDto;

public interface AccountService {

    public void deposit(AccountTransactionDto accountTransaction) throws BusinessException;

	public void addAccount(AccountDto account);

	public List<AccountDto> getAccounts();

	public void makeWithDrawal(AccountTransactionDto accountTransaction) throws BusinessException;

}
