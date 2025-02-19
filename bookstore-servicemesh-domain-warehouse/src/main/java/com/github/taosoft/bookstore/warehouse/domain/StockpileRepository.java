package com.github.taosoft.bookstore.warehouse.domain;

import com.github.taosoft.bookstore.domain.warehouse.Stockpile;
import org.springframework.data.repository.CrudRepository;

/**
 * 库存数据仓库
 **/
public interface StockpileRepository extends CrudRepository<Stockpile, Integer> {


}
