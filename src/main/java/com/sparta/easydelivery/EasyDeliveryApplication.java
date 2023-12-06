package com.sparta.easydelivery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EasyDeliveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasyDeliveryApplication.class, args);
    }

}
