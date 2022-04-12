package com.pratique.archi.hexa.persistence.jpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pratique.archi.hexa.persistence.jpa.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    Optional<Account> findByAccountNumber(String accountNum);
}
