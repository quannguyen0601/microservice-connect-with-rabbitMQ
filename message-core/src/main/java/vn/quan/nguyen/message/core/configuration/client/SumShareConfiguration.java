/**
 * SharingWork Project
 * SumShareConfiguration.java
 * Copyright © Citynow Inc. All rights reserved.
 */
package vn.quan.nguyen.message.core.configuration.client;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * SumShareConfiguration.java
 */
@Configuration
public class SumShareConfiguration {
    public static final String REPLY_SUM_QUEUE = "REPLY_SUM_QUEUE";
    public static final String REQUEST_SUM_QUEUE = "REQUEST_SUM_QUEUE";

    @Bean
    public Queue request() {
        return QueueBuilder.durable(REQUEST_SUM_QUEUE)
                .withArgument("x-dead-letter-exchange", DeadLetterQueueConfiguration.X_DEAD_LETTER_EXCHANGE)
                .withArgument("x-dead-letter-routing-key", DeadLetterQueueConfiguration.X_DEAD_LETTER_ROUTING_KEY)
                .build();
    }

    @Bean
    public Queue replies() {
        return QueueBuilder.durable(REPLY_SUM_QUEUE)
                .withArgument("x-dead-letter-exchange", DeadLetterQueueConfiguration.X_DEAD_LETTER_EXCHANGE)
                .withArgument("x-dead-letter-routing-key", DeadLetterQueueConfiguration.X_DEAD_LETTER_ROUTING_KEY)
                .build();
    }
}
