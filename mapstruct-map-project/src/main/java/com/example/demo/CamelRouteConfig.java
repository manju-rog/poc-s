package com.example.demo;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CamelRouteConfig extends RouteBuilder {

    @Autowired
    private PayloadProcessor payloadProcessor;

    @Override
    public void configure() throws Exception {
        
        from("file:C:/messages?noop=true") 
            .log("File content read: ${body}")
            .setHeader("JMSCorrelationID", simple("${id}")) 
            .to("activemq:queue:SrcQueue") 
            .log("File content sent to SrcQueue: ${body}");

       
        from("activemq:queue:SrcQueue")
        .process(exchange -> {
        	String inputPayload = exchange.getIn().getBody(String.class);
        	if (inputPayload == null) {
        	    throw new IllegalArgumentException("Message body is null or not a valid JSON string.");
        	}

            log.info("Received payload from SrcQueue: {}", inputPayload);
            payloadProcessor.saveToDatabaseWithStages(inputPayload);
            log.info("Payload processed and saved successfully.");
        })
        .log("Processed payload: ${body}")
        .to("activemq:queue:outputQueue"); 
    }
}