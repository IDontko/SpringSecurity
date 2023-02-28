package com.token;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@MapperScan("com.token.mapper")
public class TokenApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(TokenApplication.class, args);
        System.out.println(run);
    }
}
