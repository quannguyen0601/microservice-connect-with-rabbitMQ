/**
 * SharingWork Project
 * App1Client.java
 * Copyright © Citynow Inc. All rights reserved.
 */
package vn.quan.nguyen.message.app2.client;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.quan.nguyen.message.core.entity.*;

import java.util.Date;

import static vn.quan.nguyen.message.core.configuration.client.CalculationShareConfiguration.REQUEST_MINUS_QUEUE;
import static vn.quan.nguyen.message.core.configuration.client.DemoObjectConfiguration.COMPLEX_REQUEST_QUEUE;
import static vn.quan.nguyen.message.core.configuration.client.CalculationShareConfiguration.REQUEST_SUM_QUEUE;

/**
 * App1Client.java
 */
@Service
@Log4j2
public class SumService {

    @RabbitListener(queues = {REQUEST_SUM_QUEUE})
//    @SendTo(value = {REPLY_SUM_QUEUE})
    @Transactional
    public SumResponse handleSum(SumRequest request) {
        log.info("ReceiveRequest {}", request.toString());

//        if(request.getB() > 20){
//            throw new AmqpRejectAndDontRequeueException("B element is bigger than 20");
//        }

        return SumResponse.builder()
                            .sum(request.getA()+request.getB())
                            .result(new Date())
                            .build();
    }

    @RabbitListener(queues = {REQUEST_MINUS_QUEUE})
//    @SendTo(value = {REPLY_SUM_QUEUE})
    @Transactional
    public MinusResponse handleSum(MinusRequest request) {
        log.info("ReceiveMinusRequest {}", request.toString());

        return MinusResponse.builder()
                .minusResult(request.getNumberA() - request.getNumberB())
                .data(new Date())
                .build();
    }

    @RabbitListener(queues = {COMPLEX_REQUEST_QUEUE})
    public void handleComplex(DemoInput input) {
        log.info("Get complex object {}", input.toString());
        if(input.getData().equals("quan-nguyen2")){
            throw new AmqpRejectAndDontRequeueException("Error name");
        }
    }

//    @Bean(value = "sumServiceReply")
//    public SimpleMessageListenerContainer sumServiceReply(@Qualifier("rabbitSumTemplate") RabbitTemplate rabbitSumTemplate, ConnectionFactory connectionFactory) {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setMessageListener(rabbitSumTemplate);
//        container.setConnectionFactory(connectionFactory);
//        container.setQueueNames(REPLY_SUM_QUEUE);
//        return container;
//    }
//
//    @Bean(value = "sumServiceListener")
//    public SimpleMessageListenerContainer sumServiceListener(ConnectionFactory connectionFactory) {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setQueueNames(REQUEST_SUM_QUEUE);
//        container.setConnectionFactory(connectionFactory);
//        container.setMessageListener(receiver());
//        return container;
//    }
//
//    MessageListener receiver() {
//        return new MessageListenerAdapter(new SumReceiver(), "onMessage");
//    }
//
//    static class SumReceiver {
//        public String onMessage(String personName) {
//            return "Hello "+personName;
//        }
//    }
}
