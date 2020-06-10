/**
 * SharingWork Project
 * SumShareConfiguration.java
 * Copyright © Citynow Inc. All rights reserved.
 */
package vn.quan.nguyen.message.core.configuration.client;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

/**
 * SumShareConfiguration.java
 */
@Configuration
public class CalculationShareConfiguration {

    public static final String CALCULATION_EXCHANGE = "CALCULATION_EXCHANGE";

    public static final String CALCULATION_ROUTING_KEY = "calculation.";

    public static final String REPLY_SUM_QUEUE = "REPLY_SUM_QUEUE";
    public static final String REQUEST_SUM_QUEUE = "REQUEST_SUM_QUEUE";

    public static final String REPLY_MINUS_QUEUE = "REPLY_SUM_QUEUE";
    public static final String REQUEST_MINUS_QUEUE = "REQUEST_MINUS_QUEUE";

    @Bean
    public Queue requestSumQueue() {
        return QueueBuilder.durable(REQUEST_SUM_QUEUE)
                .withArgument("x-dead-letter-exchange", DeadLetterQueueConfiguration.X_DEAD_LETTER_EXCHANGE)
                .withArgument("x-dead-letter-routing-key", DeadLetterQueueConfiguration.X_DEAD_LETTER_ROUTING_KEY)
                .build();
    }

    @Bean
    public Queue requestMinusQueue() {
        return QueueBuilder.durable(REQUEST_MINUS_QUEUE)
                .withArgument("x-dead-letter-exchange", DeadLetterQueueConfiguration.X_DEAD_LETTER_EXCHANGE)
                .withArgument("x-dead-letter-routing-key", DeadLetterQueueConfiguration.X_DEAD_LETTER_ROUTING_KEY)
                .build();
    }

    @Bean
    DirectExchange calculationExchange() {
        return new DirectExchange(CALCULATION_EXCHANGE);
    }

    @Bean
    Declarables calculationBinding() {
        return new Declarables(
                BindingBuilder.bind(requestSumQueue()).to(calculationExchange()).with(CALCULATION_ROUTING_KEY+"sum"),
                BindingBuilder.bind(requestMinusQueue()).to(calculationExchange()).with(CALCULATION_ROUTING_KEY+"minus")
        );
    }


//    @Bean
//    public Queue replies() {
//        return QueueBuilder.durable(REPLY_SUM_QUEUE)
//                .withArgument("x-dead-letter-exchange", DeadLetterQueueConfiguration.X_DEAD_LETTER_EXCHANGE)
//                .withArgument("x-dead-letter-routing-key", DeadLetterQueueConfiguration.X_DEAD_LETTER_ROUTING_KEY)
//                .build();
//    }
}
