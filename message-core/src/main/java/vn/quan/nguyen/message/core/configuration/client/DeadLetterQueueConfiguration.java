/**
 * SharingWork Project
 * DeadLetterQueueConfiguration.java
 * Copyright © Citynow Inc. All rights reserved.
 */
package vn.quan.nguyen.message.core.configuration.client;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * DeadLetterQueueConfiguration.java
 */
@Configuration
public class DeadLetterQueueConfiguration {
    public static final String X_DEAD_LETTER_EXCHANGE = "ERROR_HANDLE_EXCHANGE";
    public static final String X_DEAD_LETTER_QUEUE = "ERROR_QUEUE";
    public static final String X_DEAD_LETTER_ROUTING_KEY = "error";

    @Bean
    DirectExchange deadLetterExchange() {
        return new DirectExchange(X_DEAD_LETTER_EXCHANGE);
    }

    @Bean
    Queue deadLetterQueue() {
        return QueueBuilder.durable(X_DEAD_LETTER_QUEUE).build();
    }

    @Bean
    Binding deadLetterBinding() {
        return BindingBuilder.bind(deadLetterQueue()).to(deadLetterExchange()).with(X_DEAD_LETTER_ROUTING_KEY);
    }
}
