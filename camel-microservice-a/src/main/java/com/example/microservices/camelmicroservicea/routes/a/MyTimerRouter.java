package com.example.microservices.camelmicroservicea.routes.a;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

//@Component
public class MyTimerRouter extends RouteBuilder {

    @Autowired
    private GetCurrentTimeBean getCurrentTimeBean;

    @Autowired
    private SimpleLoggingProcessingComponent simpleLoggingProcessingComponent;

    @Override
    public void configure() throws Exception {
           //

        from("timer:my-timer")
                .log("${body}")
//                .transform().constant("my const message ")
                .transform().constant("Time is: " + LocalDateTime.now() )
                .log("${body}")
                .bean(getCurrentTimeBean)
                .log("${body}")
                .bean(simpleLoggingProcessingComponent)
                .process(new SimpleLoggingProcessor())
                .to("log:first-timer");
    }


}


@Component
class GetCurrentTimeBean {
    public String getCurrentTime() {
        return "Time is: " + LocalDateTime.now();
    }

}

@Component
class SimpleLoggingProcessingComponent {

    private Logger logger = LoggerFactory.getLogger(SimpleLoggingProcessingComponent.class);
    public void process(String message){
        logger.info("Simple Processor  {}", message);
    }
}

class SimpleLoggingProcessor implements Processor {
      private Logger logger = LoggerFactory.getLogger(SimpleLoggingProcessor.class);


    @Override
    public void process(Exchange exchange) throws Exception {
        logger.info("Simple processor impl: " + exchange.getMessage().getBody());
    }
}