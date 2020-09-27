package com.cloud.seata.feign.tcc.impl;

import com.cloud.seata.feign.model.User;
import com.cloud.seata.feign.model.UserRep;
import com.cloud.seata.feign.tcc.TccSevice;
import io.seata.core.context.RootContext;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * ClassName: TccServiceImpl <br/>
 * Description: <br/>
 * date: 2020/9/27 2:57 下午<br/>
 *
 * @author tooru<br />
 */
@Slf4j
@Service
public class TccServiceImpl implements TccSevice {

    @Autowired
    UserRep userRep;

    /**
     * tcc服务t（try）方法
     * 实际业务方法
     *
     * @param params - name
     * @return String
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public String insert(@RequestBody Map<String, String> params) {
        log.info("------------------> xid = " + RootContext.getXID());
        User u = new User();
        u.setUid("tcc-feign");
        u.setMoney(500);
        //throw new RuntimeException("服务tcc测试回滚");

        User save = userRep.save(u);
        log.info("save:{}", save);
        return "success";
    }

    /**
     * tcc服务 confirm方法
     * 可以空确认
     *
     * @param context 上下文
     * @return boolean
     */
    @Override
    public boolean commitTcc(BusinessActionContext context) {
        log.info("xid = " + context.getXid() + "提交成功");
        return true;
    }

    /**
     * tcc 服务 cancel方法
     *
     * @param context 上下文
     * @return boolean
     */
    @Override
    public boolean cancel(BusinessActionContext context) {
        User u = new User();
        u.setUid("tcc-feign");
        userRep.delete(u);
        log.info("please manually rollback this data:" + context.getActionContext("params"));
        return true;
    }


}
