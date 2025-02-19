package com.github.taosoft.bookstore.paymnet.domain.repository;

import com.github.taosoft.bookstore.paymnet.domain.Payment;
import org.springframework.data.repository.CrudRepository;

/**
 * 支付单数据仓库
 **/
public interface PaymentRepository extends CrudRepository<Payment, Integer> {

    Payment getByPayId(String payId);

}
