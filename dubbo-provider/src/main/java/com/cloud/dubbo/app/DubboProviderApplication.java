package com.cloud.dubbo.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * ClassName: DubboProviderApplication <br/>
 * Description: <br/>
 * date: 2020/9/14 10:49 上午<br/>
 *
 * @author tooru<br />
 */
@EnableDiscoveryClient
@SpringBootApplication
public class DubboProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboProviderApplication.class, args);
    }

}
