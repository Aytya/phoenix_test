package com.example.phoenix_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages = {"com.example.phoenix_test", "com.example.phoenix_test.mapper"}
)
public class PhoenixTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhoenixTestApplication.class, args);
    }

}
