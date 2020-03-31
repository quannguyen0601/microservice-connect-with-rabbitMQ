/**
 * SharingWork Project
 * SumShareConfiguration.java
 * Copyright © Citynow Inc. All rights reserved.
 */
package vn.quan.nguyen.message.core.configuration.client;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * SumShareConfiguration.java
 */
@Configuration
public class SumShareConfiguration {
    public static final String REPLY_SUM_QUEUE = "REPLY_SUM_NAME";
    public static final String REQUEST_SUM_QUEUE = "REQUEST_SUM_QUEUE";

    @Bean
    public Queue request() {
        return new Queue(REQUEST_SUM_QUEUE);
    }

    @Bean
    public Queue replies() {
        return new Queue(REPLY_SUM_QUEUE);
    }

//
//
//    @Bean(value = "rabbitSumTemplate")
//    public RabbitTemplate rabbitSumTemplate(ConnectionFactory connectionFactory,
//                                            Jackson2JsonMessageConverter messageConverter) {
//        RabbitTemplate template = new RabbitTemplate(connectionFactory);
//        template.setExchange("test_exchange");
//        template.setRoutingKey("greeting");
//        template.setReplyAddress("reply_exchange"+"/"+REPLY_SUM_QUEUE);
//        template.setMessageConverter(messageConverter);
//        template.setReplyTimeout(5000);
//        return template;
//    }

}
