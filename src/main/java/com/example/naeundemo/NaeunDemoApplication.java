package com.example.naeundemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class NaeunDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(NaeunDemoApplication.class, args);
    }

}
