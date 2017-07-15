package com.example.demo;

import com.example.demo.service.FlightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.example")
@SpringBootApplication
public class AodbApplication {

    private static Logger LOG = LoggerFactory.getLogger(FlightService.class);

    public static void main(String[] args) {
        LOG.debug("Attempting to start AODB");
        SpringApplication.run(AodbApplication.class, args);
        LOG.debug("Started AODB successfully");
    }
}
