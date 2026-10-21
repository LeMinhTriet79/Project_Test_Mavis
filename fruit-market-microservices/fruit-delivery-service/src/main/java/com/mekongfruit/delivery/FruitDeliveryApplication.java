package com.mekongfruit.delivery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FruitDeliveryApplication {
    public static void main(String[] args) {
        SpringApplication.run(FruitDeliveryApplication.class, args);
    }
}
