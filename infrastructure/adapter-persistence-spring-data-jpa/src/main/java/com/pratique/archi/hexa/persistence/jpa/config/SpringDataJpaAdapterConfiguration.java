package com.pratique.archi.hexa.persistence.jpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pratique.archi.hexa.domain.spi.AccountDaoPort;
import com.pratique.archi.hexa.persistence.jpa.adapter.AccountDaoAdapter;
import com.pratique.archi.hexa.persistence.jpa.repository.AccountRepository;

@Configuration
public class SpringDataJpaAdapterConfiguration {

	@Bean
	public AccountDaoPort getAccountPersistencePort(AccountRepository accountRepository) {
		return new AccountDaoAdapter(accountRepository);
	}
}
