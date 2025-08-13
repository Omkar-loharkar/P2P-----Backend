package com.p2p;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@OpenAPIDefinition(
        info = @Info(
                title = "P2P - Pharma to Pharma",
                version = "1.0.0",
                description = "Spring Boot Based Application For All Your Pharma Needs" +
                        "  Owners - Omkar , Ankesh , Prayas , Fahad"
        )
)
@SpringBootApplication
public class EcommerceApplication {
    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }
} 