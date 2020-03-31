/**
 * SharingWork Project
 * App1Client.java
 * Copyright © Citynow Inc. All rights reserved.
 */
package vn.quan.nguyen.message.app2.client;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import static vn.quan.nguyen.message.core.configuration.client.SumShareConfiguration.REPLY_SUM_QUEUE;
import static vn.quan.nguyen.message.core.configuration.client.SumShareConfiguration.REQUEST_SUM_QUEUE;

/**
 * App1Client.java
 */
@Service
@Log4j2
public class SumService {

    @RabbitListener(queues = {REQUEST_SUM_QUEUE})
    @SendTo(value = {REPLY_SUM_QUEUE})
    public String handleSum(String name) {
        log.info("ReceiveRequest {}", name);
        return "Hello " +name;
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
