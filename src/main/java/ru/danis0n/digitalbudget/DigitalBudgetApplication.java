package ru.danis0n.digitalbudget;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class DigitalBudgetApplication {

    public static void main(String[] args) {
        SpringApplication.run(DigitalBudgetApplication.class, args);
    }

}
