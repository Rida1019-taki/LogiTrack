package org.elogitrack.logitrack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class LogiTrackApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogiTrackApplication.class, args);
    }

}
