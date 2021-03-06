package com.cloud.consumer.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * ClassName: UserFeignClient <br/>
 * Description: <br/>
 * date: 2020/9/8 10:06 上午<br/>
 *
 * @author tooru<br />
 */
//@FeignClient(name = "nacos-provider")
public interface UserFeignClient {

    @GetMapping("/user/money")
    public String money(@RequestParam Integer m);

}
