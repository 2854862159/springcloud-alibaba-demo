package com.cloud.provider;

import com.cloud.provider.model.User;
import com.cloud.provider.model.UserRep;
import com.cloud.provider.tcc.TccSevice;
import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;


@EnableAutoDataSourceProxy
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
@EnableJpaRepositories(basePackages = "com.cloud.provider.model")
public class NacosProviderDemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(NacosProviderDemoApplication.class, args);
        String userName = applicationContext.getEnvironment().getProperty("zhujing.name");
        String userAge = applicationContext.getEnvironment().getProperty("zhujing.fuck");
        System.err.println("user name :" +userName+"; fuck: "+userAge);

    }

    @RestController
    public class EchoController {

        @Autowired
        UserRep userRep;

        @Autowired
        TccSevice tccSevice;

        @GetMapping(value = "/echo/{string}")
//        @SentinelResource("hello")
        public String echo(@PathVariable String string) {
            return "Hello Nacos Discovery " + string;
        }


        @GetMapping("/user/money")
        public Object money(@RequestParam Integer m){
            User zhujing = userRep.getOne("zhujing");
            zhujing.setMoney(zhujing.getMoney() - m);

            userRep.save(zhujing);

            return Collections.<String, String>singletonMap("msg", "SUCCESS");
        }

        @GetMapping("/user/add")
        public Object add(){

            String insert = tccSevice.insert(Collections.singletonMap("msg", "ok"));

            return insert;
        }

    }
}