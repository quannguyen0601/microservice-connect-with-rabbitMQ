/**
 * SharingWork Project
 * App2Client.java
 * Copyright © Citynow Inc. All rights reserved.
 */
package vn.quan.nguyen.message.app1.client;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.quan.nguyen.message.core.configuration.client.BroadcastConfiguration;
import vn.quan.nguyen.message.core.entity.Request;

import static vn.quan.nguyen.message.core.configuration.client.SumShareConfiguration.REPLY_SUM_QUEUE;
import static vn.quan.nguyen.message.core.configuration.client.SumShareConfiguration.REQUEST_SUM_QUEUE;

/**
 * App2Client.java
 */
@RestController
@RequestMapping("/api")
public class App2Handler {

    @Autowired
//    @Qualifier("rabbitSumTemplate")
    private RabbitTemplate sumRabbitTemplate;

    @GetMapping("/request-response")
    public String sendMessage(@RequestParam("name") String name) {

        Message message = MessageBuilder.withBody(name.getBytes())
                .setContentType("text/plain")
                .setReplyTo(REPLY_SUM_QUEUE)
                .build();

        Message reply = sumRabbitTemplate.sendAndReceive(REQUEST_SUM_QUEUE,message);
        return new String(reply.getBody());
    }

    @GetMapping("/broadcast")
    public void sendBroadcast(@RequestParam("name") String content) {

        Request request = new Request();
        request.setContent(content);
        request.setType(Request.Type.APP1);

        sumRabbitTemplate.convertAndSend(BroadcastConfiguration.BROADCAST_EXCHANGE,"broadcast#",request);
    }



}
