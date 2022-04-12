package com.pratique.archi.hexa.service.api;

import com.pratique.archi.hexa.domain.exception.MaxDepositPerDayException;
import com.pratique.archi.hexa.domain.model.AccountTransactionDto;

public interface AccountService {

    public void deposit(AccountTransactionDto accountTransaction) throws MaxDepositPerDayException;

}
