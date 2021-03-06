package com.cloud.dubbo.app.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ClassName: UserRep <br/>
 * Description: <br/>
 * date: 2020/9/8 9:59 上午<br/>
 *
 * @author tooru<br />
 */
@Repository
public interface UserRep extends JpaRepository<CUser, String> {
}
