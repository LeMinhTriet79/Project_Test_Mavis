package com.mekongfruit.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FruitPaymentApplication {
    public static void main(String[] args) {
        SpringApplication.run(FruitPaymentApplication.class, args);
    }
}
