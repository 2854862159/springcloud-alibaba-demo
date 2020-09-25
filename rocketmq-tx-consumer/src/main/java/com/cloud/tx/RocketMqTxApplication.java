package com.cloud.tx;

import com.cloud.tx.rocketmq.MySink;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * ClassName: RocketMqTxApplication <br/>
 * Description: <br/>
 * date: 2020/9/25 4:23 下午<br/>
 *
 * @author tooru<br />
 */

@EnableDiscoveryClient
@SpringBootApplication
@EnableBinding({MySink.class})
public class RocketMqTxApplication {

    public static void main(String[] args) {
        SpringApplication.run(RocketMqTxApplication.class, args);
    }

}
