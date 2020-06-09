/**
 * SharingWork Project
 * App2Client.java
 * Copyright © Citynow Inc. All rights reserved.
 */
package vn.quan.nguyen.message.app1.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.quan.nguyen.message.core.configuration.client.BroadcastConfiguration;
import vn.quan.nguyen.message.core.entity.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;

import static vn.quan.nguyen.message.core.configuration.client.DemoObjectConfiguration.COMPLEX_EXCHANGE;
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

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/request-response")
    @Transactional
    public SumResponse sendMessage(@RequestParam("a") Long a, @RequestParam("b") Long b ) throws IOException {
        SumRequest request = SumRequest.builder().a(a).b(b).build();
        return sumRabbitTemplate.convertSendAndReceiveAsType(REQUEST_SUM_QUEUE,request, ParameterizedTypeReference.forType(SumResponse.class));
    }

    @GetMapping("/broadcast")
    public void sendBroadcast(@RequestParam("name") String content) {

        Request request = new Request();
        request.setContent(content);
        request.setType(Request.Type.APP1);

        sumRabbitTemplate.convertAndSend(BroadcastConfiguration.BROADCAST_EXCHANGE,"broadcast#",request);
    }

    @GetMapping("/complex")
    public void complex(@RequestParam("name") String content) {
        SmallEntity entity = SmallEntity.builder().name(content).total(BigDecimal.valueOf(150000)).build();
        DemoInput demoInput = DemoInput.builder()
                                        .data(content)
                                        .currentDate(new Date())
                                        .isFlag(true)
                                        .localDate(LocalDate.now())
                                        .localDateTime(LocalDateTime.now())
                                        .smallEntity(entity)
                                        .offsetDateTime(OffsetDateTime.now())
                                        .build();
//        SmallEntity entity =  new SmallEntity();
//        entity.setName(content);
//        entity.setTotal(BigDecimal.valueOf(150000));
//
//        DemoInput demoInput = new DemoInput();
//        demoInput.setData(content);
//        demoInput.setIsFlag(true);
//       // demoInput.setSmallEntity(entity);

        sumRabbitTemplate.convertAndSend(COMPLEX_EXCHANGE,"complex.demo", demoInput);
    }



}
