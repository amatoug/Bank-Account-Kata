package com.pratique.archi.hexa.persistence.jpa.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pratique.archi.hexa.persistence.jpa.entity.AccountTransaction;

public interface AccountTransactionRepository extends JpaRepository<AccountTransaction, Integer> {

	List<AccountTransaction> findByAccountAccountNumberAndDateBetweenAndType(String accountNumber,LocalDateTime StartOfDay, LocalDateTime endOfDay, int type);
	
	List<AccountTransaction> findByAccountAccountNumberAndDateBetweenOrderByType(String accountNumber,LocalDateTime StartOfDay, LocalDateTime endOfDay);

}
