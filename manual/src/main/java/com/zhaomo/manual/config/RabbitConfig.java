package com.zhaomo.manual.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: zhaomo
 * @Date: 16/5/2022 00:13
 * @Description:
 */
@Configuration
public class RabbitConfig {

    public static final String SAYME_QUEUE_NAME= "sayme_queue_name";
    public static final String SAYME_EXCHANGE_NAME= "sayme_exchange_name";


    @Bean
    Queue msgQueue(){
        return new Queue(SAYME_QUEUE_NAME,true,false,false);
    }

    @Bean
    DirectExchange directExchange(){
        return new DirectExchange(SAYME_EXCHANGE_NAME,true,false);
    }

    @Bean
    Binding msgBinding(){
        return BindingBuilder.bind(msgQueue())
                .to(directExchange())
                .with(SAYME_QUEUE_NAME);
    }
}
