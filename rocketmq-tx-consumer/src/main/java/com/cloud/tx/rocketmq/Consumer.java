package com.cloud.tx.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

/**
 * ClassName: Consumer <br/>
 * Description: <br/>
 * date: 2020/9/25 4:27 下午<br/>
 *
 * @author tooru<br />
 */
@Slf4j
@Component
public class Consumer {

    @StreamListener(MySink.INPUT)
    public void consumer(String message){
        log.info("收到分布式消息：{}", message);
    }
}
