package com.cloud.provider.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ClassName: User <br/>
 * Description: <br/>
 * date: 2020/9/8 9:54 上午<br/>
 *
 * @author tooru<br />
 */
@Entity
@Table(name = "user")
public class User {

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    @Id
    private String uid;

    private Integer money;



}
