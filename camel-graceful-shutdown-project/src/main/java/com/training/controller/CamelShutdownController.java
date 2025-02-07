package com.training.controller;

import org.apache.camel.spring.boot.CamelSpringBootApplicationController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CamelShutdownController implements CommandLineRunner {

    private final CamelSpringBootApplicationController controller;

    public CamelShutdownController(CamelSpringBootApplicationController controller) {
        this.controller = controller;
    }

    @Override
    public void run(String... args) throws Exception {
        controller.run(); 
    }
}
