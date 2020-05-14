package com.example.fastdfsdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example")
public class FastdfsDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(FastdfsDemoApplication.class, args);
    }

}
