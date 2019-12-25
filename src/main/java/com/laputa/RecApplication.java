package com.laputa;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan(basePackages = "com.laputa.repos")
@EnableCaching
public class RecApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecApplication.class, args);
    }

}

