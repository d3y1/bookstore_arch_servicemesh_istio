package com.github.taosoft.bookstore.warehouse.resource;

import com.github.taosoft.bookstore.infrastructure.jaxrs.CommonResponse;
import com.github.taosoft.bookstore.warehouse.application.ProductApplicationService;
import com.github.taosoft.bookstore.domain.warehouse.Product;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * 产品相关的资源
 **/

@Path("/products")
@Component
@CacheConfig(cacheNames = "resource.product")
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {

    @Inject
    ProductApplicationService service;

    /**
     * 获取仓库中所有的货物信息
     */
    @GET
    @Cacheable(key = "'ALL_PRODUCT'")
    public Iterable<Product> getAllProducts() {
        return service.getAllProducts();
    }

    /**
     * 获取仓库中指定的货物信息
     */
    @GET
    @Path("/{id}")
    @Cacheable(key = "#id")
    public Product getProduct(@PathParam("id") Integer id) {
        return service.getProduct(id);
    }

    /**
     * 更新产品信息
     */
    @PUT
    @Caching(evict = {
            @CacheEvict(key = "#product.id"),
            @CacheEvict(key = "'ALL_PRODUCT'")
    })
    public Response updateProduct(@Valid Product product) {
        return CommonResponse.op(() -> service.saveProduct(product));
    }

    /**
     * 创建新的产品
     */
    @POST
    @Caching(evict = {
            @CacheEvict(key = "#product.id"),
            @CacheEvict(key = "'ALL_PRODUCT'")
    })
    public Product createProduct(@Valid Product product) {
        return service.saveProduct(product);
    }

    /**
     * 创建新的产品
     */
    @DELETE
    @Path("/{id}")
    @Caching(evict = {
            @CacheEvict(key = "#id"),
            @CacheEvict(key = "'ALL_PRODUCT'")
    })
    public Response removeProduct(@PathParam("id") Integer id) {
        return CommonResponse.op(() -> service.removeProduct(id));
    }


}
