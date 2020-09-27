package com.cloud.dubbo.app;

import com.cloud.dubbo.app.model.UserRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RequestMapping;
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
public class DubboProviderApplication {

    @RestController
    class Rest{

        @Autowired
        UserRep rep;

        @RequestMapping("get")
        public Object get(){
            return rep.findAll();
        }

    }

    public static void main(String[] args) {
        SpringApplication.run(DubboProviderApplication.class, args);
    }

}
