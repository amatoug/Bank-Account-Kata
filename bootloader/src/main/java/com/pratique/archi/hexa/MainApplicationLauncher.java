package com.pratique.archi.hexa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.pratique.archi.hexa.service.adapter.controller.AccountController;
import com.pratique.archi.hexa.service.adapter.controller.AccountControllerImpl;

@SpringBootApplication
public class MainApplicationLauncher {
    public static void main(String[] args) {
        SpringApplication.run(MainApplicationLauncher.class, args);
    }
}
