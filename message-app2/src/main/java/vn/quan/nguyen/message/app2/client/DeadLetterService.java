/**
 * SharingWork Project
 * DeadLetterService.java
 * Copyright © Citynow Inc. All rights reserved.
 */
package vn.quan.nguyen.message.app2.client;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import vn.quan.nguyen.message.core.configuration.client.DeadLetterQueueConfiguration;

/**
 * DeadLetterService.java
 */
@Service
@Log4j2
public class DeadLetterService {
    @RabbitListener(queues = {DeadLetterQueueConfiguration.X_DEAD_LETTER_QUEUE})
    public void processFailedMessagesRequeue(Message failedMessage) {
        log.info("Received failed message, requeueing: {}", failedMessage.toString());
    }
}

