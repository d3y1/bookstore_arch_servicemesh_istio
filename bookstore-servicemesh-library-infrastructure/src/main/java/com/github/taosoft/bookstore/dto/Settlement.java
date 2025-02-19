package com.github.taosoft.bookstore.dto;

import com.github.taosoft.bookstore.domain.warehouse.Product;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Map;

/**
 * 支付结算单模型
 **/
public class Settlement {

    @Size(min = 1, message = "结算单中缺少商品清单")
    private Collection<Item> items;

    @NotNull(message = "结算单中缺少配送信息")
    private Purchase purchase;

    /**
     * 购物清单中的商品信息
     * 基于安全原因（避免篡改价格），改信息不会取客户端的，需在服务端根据商品ID再查询出来
     */
    public transient Map<Integer, Product> productMap;

    public Collection<Item> getItems() {
        return items;
    }

    public void setItems(Collection<Item> items) {
        this.items = items;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }




}
