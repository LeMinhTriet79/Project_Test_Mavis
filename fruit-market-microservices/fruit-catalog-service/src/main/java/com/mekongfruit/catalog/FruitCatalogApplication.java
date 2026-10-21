package com.mekongfruit.catalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FruitCatalogApplication {
    public static void main(String[] args) {
        SpringApplication.run(FruitCatalogApplication.class, args);
    }
}
