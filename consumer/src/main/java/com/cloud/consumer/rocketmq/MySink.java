package com.cloud.consumer.rocketmq;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * ClassName: MySink <br/>
 * Description: <br/>
 * date: 2020/9/4 4:39 下午<br/>
 *
 * @author tooru<br />
 */
public interface MySink {

    String INPUT = "zhujing-input";

    @Input(MySink.INPUT)
    SubscribableChannel input();

}
