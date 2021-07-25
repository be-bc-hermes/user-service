package com.hermes.hedwig.messageBroker.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserCommunicationInfoConfig {

    @Value("${spring.rabbitmq.queue}")
    private String communicationInfoQueueName;

    @Value("${spring.rabbitmq.routingKey}")
    private String communicationInfoRoutingKey;

    @Value("${spring.rabbitmq.exchange}")
    private String exchange;

    @Bean
    public Queue communicationInfoQueue() {
        return new Queue(communicationInfoQueueName, true);
    }

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(exchange);
    }

    @Bean
    public Binding communicationInfoBinding(final Queue queue, final DirectExchange directExchange){
        return BindingBuilder.bind(queue)
                            .to(directExchange)
                            .with(communicationInfoRoutingKey);
    }


}
