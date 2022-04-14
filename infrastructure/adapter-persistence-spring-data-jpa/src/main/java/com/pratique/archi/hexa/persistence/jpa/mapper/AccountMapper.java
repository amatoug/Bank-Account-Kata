package com.pratique.archi.hexa.persistence.jpa.mapper;

import java.util.ArrayList;
import java.util.List;

import com.pratique.archi.hexa.domain.model.AccountDto;
import com.pratique.archi.hexa.persistence.jpa.entity.Account;

/**
 * This class is a mapper class that is used to transform {@link Account} objects
 * into {@link AccountDto} objects.
 */

public final class AccountMapper {

    /**
     * Transforms the list of {@link Account} objects given as a method parameter
     * into a list of {@link AccountDto} objects and returns the created list.
     *
     * @param entities
     * @return
     */
    public static List<AccountDto> mapEntitiesIntoDTOs(Iterable<Account> entities) {
        List<AccountDto> dtos = new ArrayList<>();

        entities.forEach(e -> dtos.add(mapEntityIntoDTO(e)));

        return dtos;
    }

    /**
     * Transforms the {@link Account} object given as a method parameter into a
     * {@link AccountDto} object and returns the created object.
     *
     * @param entity
     * @return
     */
    public static AccountDto mapEntityIntoDTO(Account entity) {
    	AccountDto dto = new AccountDto();

        dto.setAccountNumber(entity.getAccountNumber());
        dto.setBalance(entity.getBalance());
        dto.setTransactions(AccountTransactionMapper.mapEntitiesIntoDTOs(entity.getTransactions()));
        return dto;
    }

}