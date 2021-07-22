package com.hermes.hedwig.messageBroker;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class Producer {

    private final RabbitTemplate template;

    @Value("${spring.rabbitmq.routingkey}")
    private String communicationInfoRoutingName;

    @Value("${spring.rabbitmq.exchange}")
    private String exchangeName;


    public void send(Message message) {
        template.convertAndSend(exchangeName, communicationInfoRoutingName, message);
        log.info("Sent message: '{}'", message);
    }


}
