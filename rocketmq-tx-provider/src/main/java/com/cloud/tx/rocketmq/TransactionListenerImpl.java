package com.cloud.tx.rocketmq;

import com.cloud.tx.model.Log;
import com.cloud.tx.rep.LogRep;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;

import java.util.UUID;


/**
 * 对需要使用分布式事务的消息发送接口监听
 * 根据事务消息分组来致性
 * ①本地事务先执行，根据业务情况执行提交、回滚操作
 * ②本地事务回查
 * ClassName: TransactionListenerImpl <br/>
 * Description: <br/>
 * date: 2020/9/25 3:00 下午<br/>
 *
 * @author tooru<br />
 */
@RocketMQTransactionListener(txProducerGroup = "myTxProducerGroup", corePoolSize = 5,
        maximumPoolSize = 10)
public class TransactionListenerImpl implements RocketMQLocalTransactionListener {

    @Autowired
    LogRep logRep;

    /**
     * 执行本地事务
     * ①事务执行成功，commit
     * ②事务执行失败，rollback
     * ③回查发送消息，unknown
     *
     * @param msg
     * @param arg
     * @return
     */
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        Object num = msg.getHeaders().get("tx-header-num");
        try {
            // 本地业务代码，事务执行
            if ("1".equals(num)) {
                System.out.println("executer: " + new String((byte[]) msg.getPayload()) + " unknown");
                return RocketMQLocalTransactionState.UNKNOWN;
            } else if ("2".equals(num)) {
                throw new Exception("Exception for RocketMQ rollback");
            }
            System.out.println("executer: " + new String((byte[]) msg.getPayload()) + " commit");

            Log log = new Log();
            log.setId(UUID.randomUUID().toString());
            log.setBusiness("ok");
            log.setForeignKey("hello");
            logRep.save(log);
            return RocketMQLocalTransactionState.COMMIT;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.out.println("executer: " + new String((byte[]) msg.getPayload()) + " rollback");
            return RocketMQLocalTransactionState.ROLLBACK;
        }

    }

    /**
     * 执行本地事务回查，当状态为 UNKNOW 会执行这个方法，回查间隔时间差不多一分钟。
     *
     * 业务代码用来检查事务当前状态，是否执行完成，如果完成就执行 COMMIT
     *
     * @param msg
     * @return
     */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        // 检查本地事务
        System.out.println("check: " + new String((byte[]) msg.getPayload()));
        return RocketMQLocalTransactionState.COMMIT;
    }

}
