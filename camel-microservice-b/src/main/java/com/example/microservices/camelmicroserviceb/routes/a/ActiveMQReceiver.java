package com.example.microservices.camelmicroserviceb.routes.a;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;


@Component
public class ActiveMQReceiver extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		
		from("activemq:my-activemq-queue")
		.log("${body}");
		
	}

}
