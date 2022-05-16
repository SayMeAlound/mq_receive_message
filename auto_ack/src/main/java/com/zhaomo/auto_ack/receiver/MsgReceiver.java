package com.zhaomo.auto_ack.receiver;

import com.zhaomo.auto_ack.config.RabbitConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Auther: zhaomo
 * @Date: 16/5/2022 00:16
 * @Description:
 */
@Component
public class MsgReceiver {

    private static final Logger logger = LoggerFactory.getLogger(MsgReceiver.class);

    @RabbitListener(queues = RabbitConfig.SAYME_QUEUE_NAME)
    public void handle(String msg){
        logger.info("msg: {}" ,msg);

        // 报错了  就不能自动确认了
        int i = 1 / 0;
    }
}
