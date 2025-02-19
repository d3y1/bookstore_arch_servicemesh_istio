package com.github.taosoft.bookstore.paymnet.application;

import com.github.taosoft.bookstore.paymnet.domain.Payment;
import com.github.taosoft.bookstore.paymnet.domain.client.ProductServiceClient;
import com.github.taosoft.bookstore.paymnet.domain.service.PaymentService;
import com.github.taosoft.bookstore.paymnet.domain.service.WalletService;
import com.github.taosoft.bookstore.dto.Settlement;
import org.springframework.cache.Cache;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

/**
 * 支付应用务
 **/
@Named
@Transactional
public class PaymentApplicationService {

    @Inject
    private PaymentService paymentService;

    @Inject
    private WalletService walletService;

    @Inject
    private ProductServiceClient productService;

    @Resource(name = "settlement")
    private Cache settlementCache;

    /**
     * 根据结算清单的内容执行，生成对应的支付单
     */
    public Payment executeBySettlement(Settlement bill) {
        // 从服务中获取商品的价格，计算要支付的总价（安全原因，这个不能由客户端传上来）
        productService.replenishProductInformation(bill);
        // 冻结部分库存（保证有货提供）,生成付款单
        Payment payment = paymentService.producePayment(bill);
        // 设立解冻定时器（超时未支付则释放冻结的库存和资金）
        paymentService.setupAutoThawedTrigger(payment);
        return payment;
    }

    /**
     * 完成支付
     * 立即取消解冻定时器，执行扣减库存和资金
     */
    public void accomplishPayment(Integer accountId, String payId) {
        // 订单从冻结状态变为派送状态，扣减库存
        double price = paymentService.accomplish(payId);
        try {
            // 扣减货款
            walletService.decrease(accountId, price);
        } catch (Throwable e) {
            // 扣款失败，将已支付的记录回滚恢复库存
            paymentService.rollbackSettlement(Payment.State.PAYED, payId);
            throw e;
        }
        // 支付成功的清除缓存
        settlementCache.evict(payId);
    }

    /**
     * 取消支付
     * 立即触发解冻定时器，释放库存和资金
     */
    public void cancelPayment(String payId) {
        // 释放冻结的库存
        paymentService.cancel(payId);
        // 支付成功的清除缓存
        settlementCache.evict(payId);
    }

}
