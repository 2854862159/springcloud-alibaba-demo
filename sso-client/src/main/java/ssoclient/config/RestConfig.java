package ssoclient.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * ClassName: RestConfig <br/>
 * Description: <br/>
 * date: 2020/9/8 3:28 下午<br/>
 *
 * @author tooru<br />
 */
@Configuration
public class RestConfig {

    @Bean("lbRestTemplate")
    @LoadBalanced
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
