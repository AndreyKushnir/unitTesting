package com.endava.ap.lotery;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@PropertySource("classpath:application.properties")
@EnableJpaRepositories(enableDefaultTransactions = false)
@Configuration
public class LoteryApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(LoteryApplication.class, args);
    }

    @Override
    public void run(String... args) {
    }
}
