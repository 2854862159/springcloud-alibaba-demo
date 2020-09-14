package com.cloud.dubbo.app;

import com.cloud.dubbo.service.HelloService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: DubboProviderApplication <br/>
 * Description: <br/>
 * date: 2020/9/14 10:49 上午<br/>
 *
 * @author tooru<br />
 */
@EnableDiscoveryClient
@SpringBootApplication
public class DubboConsumerApplication {

    @RestController
    public class Api{

        @Reference
        HelloService service;

        @RequestMapping("/hello")
        public String hello(@RequestParam String name){
            return service.hello(name);
        }

    }

    public static void main(String[] args) {
        SpringApplication.run(DubboConsumerApplication.class, args);
    }

}
