package com.training.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class CamelRoute extends RouteBuilder {
	
	private Integer counter = 0;
    @Override
    public void configure() {
        from("timer:hello?period=2000")
//        .threads(10,10)
        .process((exchange)->{
        	System.out.println("started: "+ ++counter);
        	Thread.sleep(10000);
        	System.out.println("stopped: "+ counter);

        })
            .log("Apache Camel is running...");
    }
}
