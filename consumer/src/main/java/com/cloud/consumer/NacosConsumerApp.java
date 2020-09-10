package com.cloud.consumer;

import com.cloud.consumer.model.User;
import com.cloud.consumer.model.UserRep;
import com.cloud.consumer.rocketmq.MySink;
import com.cloud.consumer.rocketmq.MySource;
import com.cloud.consumer.rocketmq.SourceProducer;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;

/**
 * ClassName: NacosConsumerApp <br/>
 * Description: <br/>
 * date: 2020/9/2 4:30 下午<br/>
 *
 * @author tooru<br />
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
@EnableJpaRepositories(basePackages = "com.cloud.consumer.model")
@EnableBinding({MySink.class, MySource.class})
//@EnableFeignClients(basePackages = "com.cloud.consumer.client")
public class NacosConsumerApp {

    @RestController
    public class NacosController {

        @Autowired
        private LoadBalancerClient loadBalancerClient;
        @Autowired
        @Qualifier("lbRestTemplate")
        private RestTemplate restTemplate;

        @Value("${spring.application.name}")
        private String appName;

        @Autowired
        SourceProducer producer;

        @GetMapping("/echo/app-name")
        public String echoAppName() {
            //Access through the combination of LoadBalanceClient and RestTemplate
            ServiceInstance serviceInstance = loadBalancerClient.choose("nacos-provider");
            String path = String.format("http://%s:%s/echo/%s", serviceInstance.getHost(), serviceInstance.getPort(), appName);
            System.out.println("request path:" + path);
            return restTemplate.getForObject(path, String.class);
        }

        @GetMapping("/echo/message")
        public void a() {
            MessageChannel messageChannel = new DirectChannel();

            // Message subscription
            ((SubscribableChannel) messageChannel).subscribe(new MessageHandler() {
                @Override
                public void handleMessage(Message<?> message) throws MessagingException {
                    System.out.println("receive msg: " + message.getPayload());
                }
            });

            // Message sending
            messageChannel.send(MessageBuilder.withPayload("simple msg").build());
        }

        @GetMapping("/echo/send")
        public void send(){
            producer.sendMessages("NMSL");
        }

        @GetMapping("/send2")
        public void send2(){
            System.out.println("NMSL");
        }

        @GetMapping("/getPrinciple")
        public OAuth2Authentication getPrinciple(OAuth2Authentication oAuth2Authentication, Principal principal, Authentication authentication) {
            System.out.println(oAuth2Authentication.getUserAuthentication().getAuthorities().toString());
            System.out.println(oAuth2Authentication.toString());
            System.out.println("principal.toString() " + principal.toString());
            System.out.println("principal.getName() " + principal.getName());
            System.out.println("authentication: " + authentication.getAuthorities().toString());

            return oAuth2Authentication;
        }

        @Autowired
        UserRep userRep;

//        @Autowired
//        UserFeignClient client;

        @GlobalTransactional
        @GetMapping("/u/m")
        public String um(){
                User zhujing = userRep.getOne("zhujing");
                zhujing.setMoney(zhujing.getMoney() + 100);

                userRep.save(zhujing);

            System.out.println(restTemplate.getForObject("http://nacos-provider/user/money?m=100", Object.class));

//            System.out.println(client.money(100));

                return "SUCCESS";
        }

    }

    //Instantiate RestTemplate Instance
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(NacosConsumerApp.class, args);
    }
}
