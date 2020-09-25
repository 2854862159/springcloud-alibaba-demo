package com.cloud.tx;

import com.cloud.tx.rocketmq.MySource;
import com.cloud.tx.rocketmq.SourceProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: RocketMqTxApplication <br/>
 * Description: <br/>
 * date: 2020/9/25 2:51 下午<br/>
 *
 * @author tooru<br />
 */
@EnableJpaRepositories
@EnableDiscoveryClient
@SpringBootApplication
@EnableBinding({MySource.class})
@RestController
public class RocketMqTxApplication {

    @Autowired
    SourceProducer sourceProducer;

    @RequestMapping("/tx")
    public void tx(){
        Map<String, String> msg = new HashMap<>();
        msg.put("name", "Nmsl");
        sourceProducer.sendTxMessage(msg, 3);
    }

    public static void main(String[] args) {
        SpringApplication.run(RocketMqTxApplication.class, args);
    }

}
