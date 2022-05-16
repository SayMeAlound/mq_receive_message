package com.zhaomo.manual.controller;


import com.zhaomo.manual.config.RabbitConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: zhaomo
 * @Date: 16/5/2022 00:17
 * @Description:
 */
@RestController
public class HelloController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping("/send")
    public void hello(){
        rabbitTemplate.convertAndSend(RabbitConfig.SAYME_EXCHANGE_NAME,RabbitConfig.SAYME_QUEUE_NAME,"hello 赵默");
    }
}
