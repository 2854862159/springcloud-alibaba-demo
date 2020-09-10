package com.cloud.consumer.rocketmq;

import org.apache.rocketmq.common.message.MessageConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: SourceProducer <br/>
 * Description: <br/>
 * date: 2020/9/4 4:40 下午<br/>
 *
 * @author tooru<br />
 */
@Component
public class SourceProducer {

        @Autowired
        private MySource source;

        public void sendMessages(String msg) {
            String payload = msg;
            Map<String, Object> headers = new HashMap<>();
            headers.put(MessageConst.PROPERTY_TAGS, "testTag");
            MessageHeaders messageHeaders = new MessageHeaders(headers);
            Message<String> message = MessageBuilder.createMessage(payload, messageHeaders);
            this.source.output().send(message);
        }

}
