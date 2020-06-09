/**
 * SharingWork Project
 * RabbitMQConfiguration.java
 * Copyright © Citynow Inc. All rights reserved.
 */
package vn.quan.nguyen.message.core.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.*;
import org.springframework.amqp.utils.SerializationUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.util.ObjectUtils;
import vn.quan.nguyen.message.core.constant.RabbitConstant;

/***
 * Stackoverflow @link[https://stackoverflow.com/questions/29592543/how-to-configure-and-receiveandconvert-json-payload-into-domain-object-in-spring]
 */


/**
 * RabbitMQConfiguration.java
 */
@Configuration
public class RabbitMQConfiguration {

    @Bean
    Queue queue() {
        return new Queue(RabbitConstant.QUEUE_NAME, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(RabbitConstant.EXCHANGE_NAME);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(RabbitConstant.ROUTING_KEY_1);
    }

    @Bean
    ObjectMapper getObjectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

    @Bean
    public RabbitTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(smartMessageConverter());
        return rabbitTemplate;
    }



//    @Bean
//    public MessageConverter jsonMessageConverter(ObjectMapper objectMapper) {
//        return new Jackson2JsonMessageConverter(objectMapper);
//    }

    @Bean
    public MessageConverter smartMessageConverter() {
        return new SmartMessageConverter() {
            @Override
            public Object fromMessage(Message message, Object conversionHint) throws MessageConversionException {
                return SerializationUtils.deserialize(message.getBody());
            }

            @Override
            public Message toMessage(Object object, MessageProperties messageProperties) throws MessageConversionException {
                Message message = new Message(SerializationUtils.serialize(object), messageProperties);
                return message;
            }

            @Override
            public Object fromMessage(Message message) throws MessageConversionException {
                return SerializationUtils.deserialize(message.getBody());
            }
        };
    }
}
