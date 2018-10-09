package com.alert.news;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Spring boot application.
 *
 * @author Shivaji Pote
 */
@SpringBootApplication
@EnableScheduling
public class NewsAlertsSystemApp {

    /**
     * Starts spring boot application.
     *
     * @param args arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(NewsAlertsSystemApp.class, args);
    }

}
