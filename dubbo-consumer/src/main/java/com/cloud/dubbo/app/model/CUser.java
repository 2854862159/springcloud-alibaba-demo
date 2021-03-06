package com.cloud.dubbo.app.model;

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
public class CUser {

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    @Id
    private String uid;

    private int money;



}
