package com.github.taosoft.bookstore.warehouse.domain;

import com.github.taosoft.bookstore.domain.warehouse.Stockpile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityNotFoundException;

/**
 * 商品库存的领域服务
 **/
@Named
public class StockpileService {

    private static final Logger log = LoggerFactory.getLogger(StockpileService.class);

    @Inject
    private StockpileRepository repository;

    /**
     * 根据产品查询库存
     */
    public Stockpile getByProductId(Integer productId) {
        return repository.findById(productId).orElseThrow(() -> new EntityNotFoundException(productId.toString()));
    }

    /**
     * 货物售出
     * 从冻结状态的货物中扣减
     */
    public void decrease(Integer productId, Integer amount) {
        Stockpile stock = repository.findById(productId).orElseThrow(() -> new EntityNotFoundException(productId.toString()));
        stock.decrease(amount);
        repository.save(stock);
        log.info("库存出库，商品：{}，数量：{}，现有库存：{}，现存冻结：{}", productId, amount, stock.getAmount(), stock.getFrozen());
    }

    /**
     * 货物增加
     * 增加指定数量货物至正常货物状态
     */
    public void increase(Integer productId, Integer amount) {
        Stockpile stock = repository.findById(productId).orElseThrow(() -> new EntityNotFoundException(productId.toString()));
        stock.increase(amount);
        repository.save(stock);
        log.info("库存入库，商品：{}，数量：{}，现有库存：{}，现存冻结：{}", productId, amount, stock.getAmount(), stock.getFrozen());
    }


    /**
     * 货物冻结
     * 从正常货物中移动指定数量至冻结状态
     */
    public void frozen(Integer productId, Integer amount) {
        Stockpile stock = repository.findById(productId).orElseThrow(() -> new EntityNotFoundException(productId.toString()));
        stock.frozen(amount);
        repository.save(stock);
        log.info("冻结库存，商品：{}，数量：{}，现有库存：{}，现存冻结：{}", productId, amount, stock.getAmount(), stock.getFrozen());
    }

    /**
     * 货物解冻
     * 从冻结货物中移动指定数量至正常状态
     */
    public void thawed(Integer productId, Integer amount) {
        Stockpile stock = repository.findById(productId).orElseThrow(() -> new EntityNotFoundException(productId.toString()));
        stock.thawed(amount);
        repository.save(stock);
        log.info("解冻库存，商品：{}，数量：{}，现有库存：{}，现存冻结：{}", productId, amount, stock.getAmount(), stock.getFrozen());
    }

    /**
     * 设置货物数量
     */
    public void set(Integer productId, Integer amount) {
        Stockpile stock = repository.findById(productId).orElseThrow(() -> new EntityNotFoundException(productId.toString()));
        stock.setAmount(amount);
        repository.save(stock);
    }

}
