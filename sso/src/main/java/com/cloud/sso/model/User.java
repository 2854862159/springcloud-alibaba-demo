package com.cloud.sso.model;

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

    @Id
    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
