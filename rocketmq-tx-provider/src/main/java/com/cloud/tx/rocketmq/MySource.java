package com.cloud.tx.rocketmq;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * ClassName: MySource <br/>
 * Description: <br/>
 * date: 2020/9/4 4:37 下午<br/>
 *
 * @author tooru<br />
 */
public interface MySource {

    @Output("rocketmq-tx-output")
    MessageChannel txOutput();

}
