/**
 * SharingWork Project
 * ProducerApp1.java
 * Copyright © Citynow Inc. All rights reserved.
 */
package vn.quan.nguyen.message.app1;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.quan.nguyen.message.core.constant.RabbitConstant;
import vn.quan.nguyen.message.core.entity.Request;
import vn.quan.nguyen.message.core.entity.Response;

/**
 * ProducerApp1.java
 */
@RestController
@RequestMapping("/api")
public class ProducerApp1 {

    @Autowired
//    @Qualifier("amqpTemplate")
    private  RabbitTemplate rabbitTemplate;

    @GetMapping("/producer")
    public Response sendMessage(@RequestParam("content") String content) {
        Request request = new Request();
        request.setContent(content);
        request.setType(Request.Type.APP1);
//        request.setDate(LocalDate.now());

        rabbitTemplate.convertAndSend(RabbitConstant.EXCHANGE_NAME, RabbitConstant.ROUTING_KEY_1, request);

        return new Response();
    }
}
