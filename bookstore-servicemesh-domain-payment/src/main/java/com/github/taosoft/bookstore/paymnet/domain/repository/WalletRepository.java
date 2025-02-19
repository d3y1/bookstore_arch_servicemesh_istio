package com.github.taosoft.bookstore.paymnet.domain.repository;

import com.github.taosoft.bookstore.paymnet.domain.Wallet;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * 钱包数据仓库
 **/
public interface WalletRepository extends CrudRepository<Wallet, Integer> {

    Optional<Wallet> findByAccountId(Integer accountId);

}
