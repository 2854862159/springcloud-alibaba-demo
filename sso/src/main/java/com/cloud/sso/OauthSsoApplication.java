package com.cloud.sso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * ClassName: OauthSsoApplication <br/>
 * Description: <br/>
 * date: 2020/9/7 10:12 上午<br/>
 *
 * @author tooru<br />
 */
@EnableDiscoveryClient
@EnableResourceServer
@SpringBootApplication
public class OauthSsoApplication {

    public static void main(String[] args) {
        SpringApplication.run(OauthSsoApplication.class, args);
    }

}
