package com.training.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class CamelRoute extends RouteBuilder {
	
//	private Integer counter = 0;	
    @Override
    public void configure() {
        from("timer:hello?period=2000")
        .threads(10,10)
        .process((exchange)->{
        	System.out.println("started: "+ Thread.currentThread().getName());
        	Thread.sleep(10000);
        	System.out.println("stopped: "+ Thread.currentThread().getName());

        })
            .log("Apache Camel is running...");
    }
}
