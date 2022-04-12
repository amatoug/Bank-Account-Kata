package com.pratique.archi.hexa.persistence.jpa.adapter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.util.CollectionUtils;

import com.pratique.archi.hexa.domain.exception.MaxDepositPerDayException;
import com.pratique.archi.hexa.domain.exception.ResourceNotFoundException;
import com.pratique.archi.hexa.domain.model.AccountTransactionDto;
import com.pratique.archi.hexa.domain.model.ResponseDto;
import com.pratique.archi.hexa.domain.model.TransactionTypeEnum;
import com.pratique.archi.hexa.domain.spi.AccountDaoPort;
import com.pratique.archi.hexa.persistence.jpa.entity.Account;
import com.pratique.archi.hexa.persistence.jpa.entity.AccountTransaction;
import com.pratique.archi.hexa.persistence.jpa.repository.AccountRepository;
import com.pratique.archi.hexa.persistence.jpa.repository.AccountTransactionRepository;

public class AccountDaoAdapter implements AccountDaoPort {

	private static final BigDecimal MAX_DEPOSIT_PER_TRANSACTION = BigDecimal.valueOf(20000);
	private static final BigDecimal MAX_DEPOSIT_PER_DAY = BigDecimal.valueOf(100000);
	private static final int MAX_DEPOSIT_TRANSACTIONS_PER_DAY = 4;

	private AccountRepository accountRepository;

	private AccountTransactionRepository accountTransactionRepository;

	private ResponseDto response = new ResponseDto();

	public AccountDaoAdapter(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	public void deposit(AccountTransactionDto accountTransaction) throws MaxDepositPerDayException {
		BigDecimal total = BigDecimal.valueOf(0.0);
		List<AccountTransaction> deposits = null;
		Optional<Account> account = accountRepository.findByAccountNumber(accountTransaction.getAccountNumber());
		if (account.isPresent()) {
			deposits = account.get().getTransactions().stream().filter(t -> TransactionTypeEnum.DEPOSIT.getId() == t.getType()).collect(Collectors.toList());
		}
		else {
			throw new ResourceNotFoundException(
					"Account number  %s is not found" + accountTransaction.getAccountNumber());
		}

		if (!CollectionUtils.isEmpty(deposits)) {
			for (AccountTransaction transaction : deposits) {
				total = total.add(transaction.getAmount());
			}
			if ((total.add(accountTransaction.getAmount())).compareTo(MAX_DEPOSIT_PER_DAY) > 0) {
				throw new MaxDepositPerDayException(
						"Deposit for the day should not be more than " + MAX_DEPOSIT_PER_DAY);
			}
		}

		if (accountTransaction.getAmount().compareTo(MAX_DEPOSIT_PER_TRANSACTION) > 0) {
			throw new MaxDepositPerDayException(
					"Deposit per transaction should not be more than " + MAX_DEPOSIT_PER_TRANSACTION);
		}
		if (!CollectionUtils.isEmpty(deposits) && deposits.size() < MAX_DEPOSIT_TRANSACTIONS_PER_DAY) {
			AccountTransaction transactionEntity = AccountTransaction.builder()
					.account(account.get()).type(TransactionTypeEnum.DEPOSIT.getId())
					.amount(accountTransaction.getAmount()).date(accountTransaction.getDate()).build();

			AccountTransaction savedTransaction = accountTransactionRepository.save(transactionEntity);

				Account accountEntity = account.get();
				accountEntity.getTransactions().add(savedTransaction);
				accountEntity.setBalance(accountEntity.getBalance().add(savedTransaction.getAmount()));

				accountRepository.save(accountEntity);

		} 

	}

}
