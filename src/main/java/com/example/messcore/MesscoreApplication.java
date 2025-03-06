package com.example.messcore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EntityScan("ezcloud.message")

public class MesscoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(MesscoreApplication.class, args);
    }

}
