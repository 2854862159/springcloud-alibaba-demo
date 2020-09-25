package com.cloud.tx.rep;

import com.cloud.tx.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ClassName: LogRep <br/>
 * Description: <br/>
 * date: 2020/9/25 4:18 下午<br/>
 *
 * @author tooru<br />
 */
public interface LogRep extends JpaRepository<Log, String> {
}
