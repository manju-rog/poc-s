package com.training.route;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class CamelRoute extends RouteBuilder {
	
//	private Integer counter = 0;	
    @Override
    public void configure() {
    	
    	ExecutorService executorService = Executors.newCachedThreadPool();
    	
        from("timer:hello?period=2000")
//        .threads(10,10)
        .threads().executorService(executorService)
        .process((exchange)->{
        	System.out.println("started: "+ Thread.currentThread().getName());
        	System.out.println("Start time: "+ LocalDateTime.now());
        	Thread.sleep(45000);
        	System.out.println("stopped: "+ Thread.currentThread().getName());
        	System.out.println("Stop time: "+ LocalDateTime.now());

        })
            .log("Apache Camel is running...");
    }
}
