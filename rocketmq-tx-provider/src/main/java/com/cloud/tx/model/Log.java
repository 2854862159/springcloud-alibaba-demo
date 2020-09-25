package com.cloud.tx.model;

import javax.persistence.Column;
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
@Table(name = "transaction_log")
public class Log {

    @Id
    private String id;

    private String business;

    @Column(name = "foreign_key")
    private String foreignKey;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getForeignKey() {
        return foreignKey;
    }

    public void setForeignKey(String foreignKey) {
        this.foreignKey = foreignKey;
    }
}
