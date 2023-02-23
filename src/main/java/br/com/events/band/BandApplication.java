package br.com.events.band;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class BandApplication {

    public static void main(String[] args) {
        SpringApplication.run(BandApplication.class, args);
    }

}
