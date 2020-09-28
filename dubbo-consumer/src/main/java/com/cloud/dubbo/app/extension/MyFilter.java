package com.cloud.dubbo.app.extension;

import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

/**
 * ClassName: MyFilter <br/>
 * Description: <br/>
 * date: 2020/9/28 2:58 下午<br/>
 *
 * @author tooru<br />
 */
@Activate(group = {CommonConstants.CONSUMER})
public class MyFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        System.err.println("my filter");
        // before filter ...
        Result result = invoker.invoke(invocation);
        // after filter ...
        return result;
    }
}
