package com.github.taosoft.bookstore.warehouse.application;

import com.github.taosoft.bookstore.domain.warehouse.Product;
import com.github.taosoft.bookstore.warehouse.domain.ProductService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

/**
 * 产品的应用服务接口
 **/
@Named
@Transactional
public class ProductApplicationService {

    @Inject
    private ProductService service;

    /**
     * 获取仓库中所有的货物信息
     */
    public Iterable<Product> getAllProducts() {
        return service.getAllProducts();
    }

    /**
     * 获取仓库中指定的货物信息
     */
    public Product getProduct(Integer id) {
        return service.getProduct(id);
    }

    /**
     * 创建或更新产品信息
     */
    public Product saveProduct(Product product) {
        return service.saveProduct(product);
    }

    /**
     * 删除指定产品
     */
    public void removeProduct(Integer id) {
        service.removeProduct(id);
    }

}
