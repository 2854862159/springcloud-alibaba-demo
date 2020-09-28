package com.cloud.dubbo.app.extension;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.cluster.LoadBalance;

import java.util.List;

/**
 * ClassName: MyLoadBalance <br/>
 * Description: <br/>
 * date: 2020/9/28 10:20 上午<br/>
 *
 * @author tooru<br />
 */
public class MyLoadBalance implements LoadBalance {

    @Override
    public <T> Invoker<T> select(List<Invoker<T>> invokers, URL url, Invocation invocation) throws RpcException {
        System.err.println("my loadbalance go");
        return invokers.get(0);
    }

}
