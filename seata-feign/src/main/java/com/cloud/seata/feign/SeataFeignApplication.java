package com.cloud.seata.feign;

import com.cloud.seata.feign.client.UserFeignClient;
import com.cloud.seata.feign.model.User;
import com.cloud.seata.feign.model.UserRep;
import com.cloud.seata.feign.tcc.TccSevice;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

/**
 * ClassName: SeataFeignApplication <br/>
 * Description: <br/>
 * date: 2020/9/10 10:54 上午<br/>
 *
 * @author tooru<br />
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
@EnableJpaRepositories(basePackages = "com.cloud.seata.feign.model")
@EnableFeignClients(basePackages = "com.cloud.seata.feign.client")
public class SeataFeignApplication {

    @RestController
    public class Api{

        @Autowired
        UserRep userRep;

        @Autowired
        UserFeignClient client;

        @Autowired
        TccSevice tccSevice;

        @GlobalTransactional
        @GetMapping("/u/m")
        public String um(){
            User zhujing = userRep.getOne("zhujing");
            zhujing.setMoney(zhujing.getMoney() + 100);

            userRep.save(zhujing);

            System.out.println(client.money(100));

            return "SUCCESS";
        }


        @GlobalTransactional
        @Transactional
        @GetMapping("/add")
        public String add(){
            Map<String, String> message = Collections
                    .unmodifiableMap(Collections.<String, String>singletonMap("message",
                            "has refresh, say you later..."));
            tccSevice.insert(message);
            client.add();

            return "SUCCESS";
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(SeataFeignApplication.class, args);
    }

}
