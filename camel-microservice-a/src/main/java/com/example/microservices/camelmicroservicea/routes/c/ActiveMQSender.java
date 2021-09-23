package com.example.microservices.camelmicroservicea.routes.c;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQSender extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		
		from("timer:active-mq-timer?period=10000")
		.transform().constant("My msg to mq")
		.log("${body}")
		.to("activemq:my-activemq-queue");
		
		
	}

}
