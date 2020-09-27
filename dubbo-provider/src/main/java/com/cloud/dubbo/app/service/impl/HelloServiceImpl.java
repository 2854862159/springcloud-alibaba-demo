package com.cloud.dubbo.app.service.impl;

import com.cloud.dubbo.app.model.PUser;
import com.cloud.dubbo.app.model.UserRep;
import com.cloud.dubbo.service.HelloService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * ClassName: HelloServiceImpl <br/>
 * Description: <br/>
 * date: 2020/9/14 10:53 上午<br/>
 *
 * @author tooru<br />
 */
@Service
public class HelloServiceImpl implements HelloService {

    @Autowired
    UserRep userRep;

    @Transactional
    @Override
    public String hello(String name) {
//        PUser pUser = new PUser();
//        pUser.setUid("dubbo");
//        Example<PUser> of = Example.of(pUser);
//
//        Optional<PUser> dubbo = userRep.findOne(of);
//        PUser pUser1 = dubbo.get();
//        pUser1.setMoney(pUser1.getMoney() - 100);
//
//        userRep.save(pUser1);

        PUser dubbo = userRep.getOne("dubbo");
        dubbo.setMoney(dubbo.getMoney() - 100);
        userRep.save(dubbo);


//        System.out.println(1/0);
        return "Hello" + name;
    }

}
