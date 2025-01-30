package com.training.controller;

import org.apache.camel.CamelContext;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ShutdownController {

    private final CamelContext camelContext;
    private final ApplicationContext applicationContext;

    public ShutdownController(CamelContext camelContext, ApplicationContext applicationContext) {
        this.camelContext = camelContext;
        this.applicationContext = applicationContext;
    }

    @GetMapping("/shutdown")
    public String shutdownApplication() {
        new Thread(() -> {
            try {
                 // Allow response to be sent before shutting down
                camelContext.stop(); // Gracefully stops Apache Camel
                SpringApplication.exit(applicationContext, () -> 0); // Gracefully stops Spring Boot
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        return "Shutdown initiated...";
    }
}
