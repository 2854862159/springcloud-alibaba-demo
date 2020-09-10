package com.cloud.consumer.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

/**
 * ClassName: SinkConsumer <br/>
 * Description: <br/>
 * date: 2020/9/4 4:47 下午<br/>
 *
 * @author tooru<br />
 */
@Component
@Slf4j
public class SinkConsumer {

    @StreamListener(MySink.INPUT)
    public void inputConsumer(String message) {
        log.info("从Binding-{}收到信息-{}", MySink.INPUT, message);
    }

}
