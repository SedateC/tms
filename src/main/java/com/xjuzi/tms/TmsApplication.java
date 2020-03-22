package com.xjuzi.tms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TmsApplication {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(TmsApplication.class);
        logger.info("helloWorld");
        SpringApplication.run(TmsApplication.class, args);
    }

}
