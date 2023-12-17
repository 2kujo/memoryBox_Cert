package com.memorybox;

import com.memorybox.controller.CertificationController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MemoryboxCertApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemoryboxCertApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(CertificationController certificationController) {
        return (String[] args) -> {
            for (int userId = 1; userId <= 10; userId++) {
                certificationController.addUserId(userId);
            }
        };
    }
}
