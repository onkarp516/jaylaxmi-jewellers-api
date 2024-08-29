package com.truethic.jaylaxmi.JaylaxmiJewellersAPI;

import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.reporting.FileExporter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JaylaxmiJewellersApplication {
    public static void main(String[] args) {

        SpringApplication.run(JaylaxmiJewellersApplication.class, args);
        System.out.println("Successfully Executed.....!");
    }

    @Bean
    public FileExporter fileExporter() {
        return new FileExporter();
    }
}
