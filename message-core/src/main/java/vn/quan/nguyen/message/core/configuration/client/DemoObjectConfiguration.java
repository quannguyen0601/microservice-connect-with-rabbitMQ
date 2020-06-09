/**
 * SharingWork Project
 * DemoObjectConfiguration.java
 * Copyright © Citynow Inc. All rights reserved.
 */
package vn.quan.nguyen.message.core.configuration.client;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * DemoObjectConfiguration.java
 */
@Configuration
@Log4j2
public class DemoObjectConfiguration {
    public static final String COMPLEX_REQUEST_QUEUE = "COMPLEX_REQUEST_QUEUE";
    public static final String COMPLEX_EXCHANGE = "COMPLEX_EXCHANGE";

    @Bean
    public Queue queueComplex() {
        return QueueBuilder.durable(COMPLEX_REQUEST_QUEUE)
                .withArgument("x-dead-letter-exchange", DeadLetterQueueConfiguration.X_DEAD_LETTER_EXCHANGE)
                .withArgument("x-dead-letter-routing-key", DeadLetterQueueConfiguration.X_DEAD_LETTER_ROUTING_KEY)
                .build();
    }

    @Bean
    public Exchange exchangeComplex() {
        return new TopicExchange(COMPLEX_EXCHANGE);
    }

    @Bean
    public Binding complexBinding(){
        return BindingBuilder.bind(queueComplex()).to(exchangeComplex()).with("complex.#").noargs();
    }
}
