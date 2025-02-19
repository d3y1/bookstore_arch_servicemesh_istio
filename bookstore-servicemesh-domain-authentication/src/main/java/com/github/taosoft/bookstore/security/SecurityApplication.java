package com.github.taosoft.bookstore.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SprintCloud Security 认证、授权服务
 **/
@SpringBootApplication(scanBasePackages = {"com.github.taosoft.bookstore"})
public class SecurityApplication {
    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }
}
