package com.cloud.dubbo.app.service.impl;

import com.cloud.dubbo.service.HelloService;
import org.apache.dubbo.config.annotation.Service;

/**
 * ClassName: HelloServiceImpl <br/>
 * Description: <br/>
 * date: 2020/9/14 10:53 上午<br/>
 *
 * @author tooru<br />
 */
@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(String name) {
        return "Hello" + name;
    }
}
