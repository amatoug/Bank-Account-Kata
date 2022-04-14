package com.pratique.archi.hexa.persistence.jpa.mapper;

import java.util.ArrayList;
import java.util.List;

import com.pratique.archi.hexa.domain.model.AccountDto;
import com.pratique.archi.hexa.domain.model.AccountTransactionDto;
import com.pratique.archi.hexa.domain.model.TransactionTypeEnum;
import com.pratique.archi.hexa.persistence.jpa.entity.Account;
import com.pratique.archi.hexa.persistence.jpa.entity.AccountTransaction;

/**
 * This class is a mapper class that is used to transform {@link Account} objects
 * into {@link AccountDto} objects.
 */
public final class AccountTransactionMapper {

    /**
     * Transforms the list of {@link AccountTransaction} objects given as a method parameter
     * into a list of {@link AccountTransactionDto} objects and returns the created list.
     *
     * @param entities
     * @return
     */
	public static List<AccountTransactionDto> mapEntitiesIntoDTOs(Iterable<AccountTransaction> entities) {
        List<AccountTransactionDto> dtos = new ArrayList<>();

        entities.forEach(e -> dtos.add(mapEntityIntoDTO(e)));

        return dtos;
    }

    /**
     * Transforms the {@link AccountTransaction} object given as a method parameter into a
     * {@link AccountTransactionDto} object and returns the created object.
     *
     * @param entity
     * @return
     */
	public static AccountTransactionDto mapEntityIntoDTO(AccountTransaction entity) {
    	AccountTransactionDto dto = new AccountTransactionDto();

    	dto.setAccountNumber(entity.getAccount().getAccountNumber());
    	dto.setAmount(entity.getAmount());
    	dto.setType(TransactionTypeEnum.findByCode(entity.getType()));
    	dto.setDate(entity.getDate());
        return dto;
    }

}