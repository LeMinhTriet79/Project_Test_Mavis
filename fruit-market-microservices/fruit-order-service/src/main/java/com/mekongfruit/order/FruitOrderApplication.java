package com.mekongfruit.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FruitOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(FruitOrderApplication.class, args);
    }
}
