package com.pratique.archi.hexa.persistence.jpa.adapter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.pratique.archi.hexa.domain.exception.BusinessException;
import com.pratique.archi.hexa.domain.exception.ResourceNotFoundException;
import com.pratique.archi.hexa.domain.model.AccountDto;
import com.pratique.archi.hexa.domain.model.AccountTransactionDto;
import com.pratique.archi.hexa.domain.model.ResponseDto;
import com.pratique.archi.hexa.domain.model.TransactionTypeEnum;
import com.pratique.archi.hexa.domain.spi.AccountDaoPort;
import com.pratique.archi.hexa.persistence.jpa.entity.Account;
import com.pratique.archi.hexa.persistence.jpa.entity.AccountTransaction;
import com.pratique.archi.hexa.persistence.jpa.mapper.AccountTransactionMapper;
import com.pratique.archi.hexa.persistence.jpa.repository.AccountRepository;
import com.pratique.archi.hexa.persistence.jpa.repository.AccountTransactionRepository;

public class AccountDaoAdapter implements AccountDaoPort {

	private static final BigDecimal MAX_DEPOSIT_PER_TRANSACTION = BigDecimal.valueOf(20000);
	private static final BigDecimal MAX_DEPOSIT_PER_DAY = BigDecimal.valueOf(100000);
	private static final int MAX_DEPOSIT_TRANSACTIONS_PER_DAY = 4;
	
	
	
    private static final BigDecimal MAX_WITHDRAWAL_PER_TRANSACTION = BigDecimal.valueOf(20000); 
    private static final BigDecimal MAX_WITHDRAWAL_PER_DAY = BigDecimal.valueOf(10000); 
    private static final int MAX_WITHDRAWAL_TRANSACTIONS_PER_DAY = 5;
    

	private AccountRepository accountRepository;

	@Autowired
	private AccountTransactionRepository accountTransactionRepository;

	private ResponseDto response = new ResponseDto();

	public AccountDaoAdapter(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	
	@Transactional
	public void deposit(AccountTransactionDto accountTransaction) throws BusinessException {
		BigDecimal total = BigDecimal.valueOf(0.0);
		Optional<Account> account = accountRepository.findByAccountNumber(accountTransaction.getAccountNumber());
		if (!account.isPresent()) {
			throw new ResourceNotFoundException(
					"Account number  " + accountTransaction.getAccountNumber() + " is not found");
		}

		
		LocalDate currentDay =  LocalDate.now();
		List<AccountTransaction> deposits = accountTransactionRepository.findByAccountAccountNumberAndDateBetweenAndType(accountTransaction.getAccountNumber(), LocalDate.now().atStartOfDay(), LocalTime.MAX.atDate(currentDay), TransactionTypeEnum.DEPOSIT.getCode());


		if (!CollectionUtils.isEmpty(deposits)) {
			for (AccountTransaction transaction : deposits) {
				total = total.add(transaction.getAmount());
			}
			if ((total.add(accountTransaction.getAmount())).compareTo(MAX_DEPOSIT_PER_DAY) > 0) {
				throw new BusinessException(
						"Deposit for the day should not be more than " + MAX_DEPOSIT_PER_DAY);
			}
		}

		if (accountTransaction.getAmount().compareTo(MAX_DEPOSIT_PER_TRANSACTION) > 0) {
			throw new BusinessException(
					"Deposit per transaction should not be more than " + MAX_DEPOSIT_PER_TRANSACTION);
		}
		if (deposits != null && deposits.size() < MAX_DEPOSIT_TRANSACTIONS_PER_DAY) {
			AccountTransaction transactionEntity = AccountTransaction.builder()
					.account(account.get()).type(TransactionTypeEnum.DEPOSIT.getCode())
					.amount(accountTransaction.getAmount()).date(accountTransaction.getDate()).build();

			AccountTransaction savedTransaction = accountTransactionRepository.save(transactionEntity);

				Account accountEntity = account.get();
				accountEntity.getTransactions().add(savedTransaction);
				accountEntity.setBalance(accountEntity.getBalance().add(savedTransaction.getAmount()));

				accountRepository.save(accountEntity);

		} 
		else {
			throw new BusinessException(
					"Maximum number of Transactions per Day reached" + MAX_DEPOSIT_TRANSACTIONS_PER_DAY);

		}

	}

	@Override
	public void addAccount(AccountDto accountDto) {
	Account account  = Account.builder()
			.accountNumber(accountDto.getAccountNumber())
			.balance(accountDto.getBalance())
			.build();
	
	accountRepository.save(account);
		
	}

	@Override
	public List<AccountDto> getAccounts() {
        List<AccountDto> accountDtoList = new ArrayList<AccountDto>();
        List<Account> accountList = accountRepository.findAll();
        for(Account entity : accountList) {
        	AccountDto accountDto = new AccountDto();
        	accountDto.setAccountNumber(entity.getAccountNumber());
        	accountDto.setBalance(entity.getBalance());
        	accountDtoList.add(accountDto);
        }
        return accountDtoList;
	}


	@Transactional
	public void makeWithDrawal(AccountTransactionDto accountTransaction) throws BusinessException {

    		BigDecimal total = BigDecimal.valueOf(0.0);

    		Optional<Account> account = accountRepository.findByAccountNumber(accountTransaction.getAccountNumber());
    		if (!account.isPresent()) {
    			throw new ResourceNotFoundException(
    					"Account number  " + accountTransaction.getAccountNumber() + " is not found");
    		}

    		
            if (accountTransaction.getAmount().compareTo(account.get().getBalance())> 0) {
            	throw new BusinessException(
						"not enough money in your account for debit");
			}



            // check maximum limit withdrawal for the day has been reached
        
			LocalDate currentDay =  LocalDate.now();
			List<AccountTransaction> withdrawals  = accountTransactionRepository.findByAccountAccountNumberAndDateBetweenAndType(accountTransaction.getAccountNumber(), LocalDate.now().atStartOfDay(), LocalTime.MAX.atDate(currentDay), TransactionTypeEnum.WITHDRAWAL.getCode());


            if (!CollectionUtils.isEmpty(withdrawals)) {
            	
            	BigDecimal solde = withdrawals.stream()
                .map(t -> t.getAmount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);  
            	

                if (solde.add(accountTransaction.getAmount()).compareTo(MAX_WITHDRAWAL_PER_DAY) > 0) {
                	throw new BusinessException(
    						"Withdrawal per day should not be more than "+MAX_WITHDRAWAL_PER_DAY);
                }
            }

            // Check whether the amount being withdrawn exceeds the MAX_WITHDRAWAL_PER_TRANSACTION
            if (accountTransaction.getAmount().compareTo(MAX_WITHDRAWAL_PER_TRANSACTION) > 0) {
            	throw new BusinessException(
						"Exceeded Maximum Withdrawal Per Transaction "+MAX_WITHDRAWAL_PER_TRANSACTION);
            }

            // check whether transactions exceeds the max allowed per day
            if (withdrawals!= null && withdrawals.size() < MAX_WITHDRAWAL_TRANSACTIONS_PER_DAY) {
    			AccountTransaction transactionEntity = AccountTransaction.builder()
    					.account(account.get()).type(TransactionTypeEnum.WITHDRAWAL.getCode())
    					.amount(accountTransaction.getAmount()).date(accountTransaction.getDate()).build();

            	
    			AccountTransaction savedTransaction = accountTransactionRepository.save(transactionEntity);

				Account accountEntity = account.get();
				accountEntity.getTransactions().add(savedTransaction);
				accountEntity.setBalance(accountEntity.getBalance().subtract(savedTransaction.getAmount()));

				accountRepository.save(accountEntity);


            }
    		else {
    			throw new BusinessException(
    					"Maximum Withdrawal Transactions per Day Exceeded" + MAX_DEPOSIT_TRANSACTIONS_PER_DAY);

    		}


		
	}


	@Override
	public List<AccountTransactionDto> getAccountHistoryByAccountNumberAndPeriod(String accountNumber, LocalDateTime fromDate,
			LocalDateTime toDate) {
		List<AccountTransaction> transactions = accountTransactionRepository.findByAccountAccountNumberAndDateBetweenOrderByType(accountNumber, fromDate, toDate);
		return AccountTransactionMapper.mapEntitiesIntoDTOs(transactions);	
}


}
