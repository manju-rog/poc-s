package com.example.demo;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQXAConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class CamelRouteConfig extends RouteBuilder {

    @Autowired
    private PayloadProcessor payloadProcessor;

    @Override
    public void configure() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        
        ActiveMQXAConnectionFactory activeXaCf =
                new ActiveMQXAConnectionFactory("tcp://localhost:61616");
        activeXaCf.setUserName("admin");
        activeXaCf.setPassword("admin");
        activeXaCf.setTrustAllPackages(true);
        
        JmsComponent activeMqComponent = JmsComponent.jmsComponentTransacted(activeXaCf);
        
        CamelContext camelContext = new DefaultCamelContext();
        camelContext.addComponent("activemqXA", activeMqComponent);
        
        camelContext.addRoutes(new RouteBuilder() {
			@Override
			public void configure() throws Exception {
				// Routes will be defined here
				System.out.println("Routes are being configured...");
//				from("file:C:/messages?noop=true").transacted() 
//	            .log("File content read: ${body}")
////	            .filter().method(this, "isValidJson")
//	            .setHeader("JMSCorrelationID", simple("${id}")) 
//	            .to("activemqXA:queue:SrcQueue") 
//	            .log("File content sent to SrcQueue: ${body}");

	        from("activemqXA:queue:SrcQueue")
//	        .process(exchange -> {
//	            String inputPayload = exchange.getIn().getBody(String.class);
//	            if (inputPayload == null) {
//	                throw new IllegalArgumentException("Message body is null or not a valid JSON string.");
//	            }
//	            try {
//	                log.info("Received payload from SrcQueue: {}", inputPayload);
//	                payloadProcessor.saveToDatabaseWithStages(inputPayload);
//	                log.info("Payload processed and saved successfully.");
//	            } catch (JsonProcessingException e) {
//	                log.error("JSON parsing error: {}", e.getMessage());
//	                // Optionally, send to dead letter queue or skip
//	            } catch (Exception e) {
//	                log.error("Processing error: {}", e.getMessage());
//	                // Optionally, send to dead letter queue or skip
//	            }
//	        })
	        .log("Processed payload: ${body}")
	        .to("activemqXA:queue:outputQueue"); 
	    }
		});
        camelContext.start();}
    
        

    public boolean isValidJson(String body) {
        if (body == null) return false;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.readTree(body);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}