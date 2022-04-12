package com.pratique.archi.hexa.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pratique.archi.hexa.domain.spi.AccountDaoPort;
import com.pratique.archi.hexa.service.adapter.AccountServiceAdapter;
import com.pratique.archi.hexa.service.api.AccountService;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public AccountService getAccountService(AccountDaoPort accountPersistencePort) {
        return new AccountServiceAdapter(accountPersistencePort);
    }
}
