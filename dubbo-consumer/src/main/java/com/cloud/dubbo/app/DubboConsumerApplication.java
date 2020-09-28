package com.cloud.dubbo.app;

import com.cloud.dubbo.app.extension.MyLoadBalance;
import com.cloud.dubbo.app.model.CUser;
import com.cloud.dubbo.app.model.UserRep;
import com.cloud.dubbo.service.HelloService;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.rpc.cluster.LoadBalance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
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
@EnableJpaRepositories
@EnableDiscoveryClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class DubboConsumerApplication {

    @RestController
    public class Api{

        @Reference(loadbalance = "myloadbalance")
        HelloService service;

        @Autowired
        UserRep userRep;

        @GlobalTransactional
        @RequestMapping("/hello")
        public String hello(@RequestParam String name){
            CUser dubbo = userRep.getOne("dubbo");
            dubbo.setMoney(dubbo.getMoney() + 100);

            userRep.save(dubbo);

            String hello = service.hello(name);
            return hello;
        }

        @RequestMapping("/myload")
        public void myload(){
            ExtensionLoader<LoadBalance> extensionLoader =
                    ExtensionLoader.getExtensionLoader(LoadBalance.class);
            LoadBalance myloadbalance = extensionLoader.getExtension("myloadbalance");
            System.out.println(123);
        }

    }

    public static void main(String[] args) {
        SpringApplication.run(DubboConsumerApplication.class, args);
    }

}
