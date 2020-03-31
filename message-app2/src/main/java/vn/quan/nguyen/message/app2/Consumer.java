/**
 * SharingWork Project
 * ProducerController.java
 * Copyright © Citynow Inc. All rights reserved.
 */
package vn.quan.nguyen.message.app2;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import vn.quan.nguyen.message.core.configuration.client.BroadcastConfiguration;
import vn.quan.nguyen.message.core.constant.RabbitConstant;
import vn.quan.nguyen.message.core.entity.Request;

/**
 * ProducerController.java
 */
@RequiredArgsConstructor
@Component
@Log4j2
public class Consumer {

    @RabbitListener(queues = RabbitConstant.QUEUE_NAME)
    public void handleMessage(Request content) {
        log.info("Get message {}", content);
    }

    @RabbitListener(queues = BroadcastConfiguration.BROADCAST_QUEUE1)
    public void handleBroadCast1(Request content) {
        log.info("Get message from broadcast 1 {}", content);
    }

    @RabbitListener(queues = BroadcastConfiguration.BROADCAST_QUEUE2)
    public void handleBroadCast2(Request content) {
        log.info("Get message from broadcast 2 {}", content);
    }

    @RabbitListener(queues = BroadcastConfiguration.BROADCAST_QUEUE3)
    public void handleBroadCast3(Request content) {
        log.info("Get message from broadcast 3 {}", content);
    }

}
