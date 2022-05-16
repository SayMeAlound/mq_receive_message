package com.zhaomo.manual.receiver;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.zhaomo.manual.config.RabbitConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Auther: zhaomo
 * @Date: 16/5/2022 00:16
 * @Description:
 */
//@Component
public class MsgReceiver {

    private static final Logger logger = LoggerFactory.getLogger(MsgReceiver.class);

    @RabbitListener(queues = RabbitConfig.SAYME_QUEUE_NAME)
    public void handle(Message  message, Channel channel){
        // 获取消息的一个标记
        long deliveryTag = message.getMessageProperties().getDeliveryTag();

        try {
            // 开始消息的消费
            byte[] body = message.getBody();

            String s = new String(body);
            logger.info("handleMsg: {}",s);
            int i =  1 / 0;
            // 消费完成之后  手动ack
            // 第一个参数 是消息的标记  第二个参数为 false  表示仅仅确认当前消息,  如果为 true  表示之前的所有消息都确认消费成功
            channel.basicAck(deliveryTag,false);
        } catch (Exception e) {
            try {
                //  手动 nack   告诉 Mq 这条消息消费失败
                channel.basicNack(deliveryTag,false,true);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
