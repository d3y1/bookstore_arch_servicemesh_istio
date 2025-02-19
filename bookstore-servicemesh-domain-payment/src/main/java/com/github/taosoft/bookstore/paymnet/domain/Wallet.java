package com.github.taosoft.bookstore.paymnet.domain;

import com.github.taosoft.bookstore.domain.BaseEntity;

import javax.persistence.Entity;

/**
 * 用户钱包
 **/

@Entity
public class Wallet extends BaseEntity {

    public Wallet() {
        // for JPA
    }

    public Wallet(Integer accountId, Double money) {
        setAccountId(accountId);
        setMoney(money);
    }

    // 这里是偷懒，正式项目中请使用BigDecimal来表示金额
    private Double money;

    private Integer accountId;

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }
}
