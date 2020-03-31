/**
 * SharingWork Project
 * BroadcastConfiguration.java
 * Copyright QuanNguyen All rights reserved.
 */
package vn.quan.nguyen.message.core.configuration.client;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * BroadcastConfiguration.java
 */
@Configuration
public class BroadcastConfiguration {
    public static final String BROADCAST_QUEUE1 = "BROADCAST_QUEUE1";
    public static final String BROADCAST_QUEUE2 = "BROADCAST_QUEUE2";
    public static final String BROADCAST_QUEUE3 = "BROADCAST_QUEUE3";
    public static final String BROADCAST_EXCHANGE = "BROADCAST_EXCHANGE";

    @Bean
    public Queue  broadCastQueue1() {
        return new Queue(BROADCAST_QUEUE1);
    }
    @Bean
    public Queue  broadCastQueue2() {
        return new Queue(BROADCAST_QUEUE2);
    }
    @Bean
    public Queue  broadCastQueue3() {
        return new Queue(BROADCAST_QUEUE3);
    }

    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange(BROADCAST_EXCHANGE);
    }

    @Bean
    public Binding broadcastBiding1(){
       return BindingBuilder.bind(broadCastQueue1()).to(fanoutExchange());
    }

    @Bean
    public Binding broadcastBiding2(){
        return BindingBuilder.bind(broadCastQueue2()).to(fanoutExchange());
    }

    @Bean
    public Binding broadcastBiding3(){
        return BindingBuilder.bind(broadCastQueue3()).to(fanoutExchange());
    }

}
