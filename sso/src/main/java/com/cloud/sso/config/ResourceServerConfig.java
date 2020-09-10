package com.cloud.sso.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * ClassName: ResourceServerConfig <br/>
 * Description: <br/>
 * date: 2020/9/10 4:57 下午<br/>
 *
 * @author tooru<br />
 */
@Order(6)
@Configuration
@EnableResourceServer //这个类表明了此应用是OAuth2 的资源服务器，此处主要指定了受资源服务器保护的资源链接
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    RedisTokenStore redisTokenStore;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId("resource3").tokenStore(redisTokenStore);
    }


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .requestMatchers()
                .antMatchers("/user/**");//配置需要保护的资源路径
    }

}
