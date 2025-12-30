package com.etude.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.etude.api"})
public class HotelApiComparisonApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotelApiComparisonApplication.class, args);
    }
}
