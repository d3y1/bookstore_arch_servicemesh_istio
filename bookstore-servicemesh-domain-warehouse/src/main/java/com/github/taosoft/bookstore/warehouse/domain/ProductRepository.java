package com.github.taosoft.bookstore.warehouse.domain;

import com.github.taosoft.bookstore.domain.warehouse.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

/**
 * 商品对象数据仓库
 **/
public interface ProductRepository extends CrudRepository<Product, Integer> {

    Collection<Product> findByIdIn(Collection<Integer> ids);

}
