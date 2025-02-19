package com.github.taosoft.bookstore.infrastructure.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

/**
 * 配置JPA EntityManager的搜索范围
 * 避免各个工程由于Application文件在此包名的下级而导致基础包中的Entity搜索不到
 **/
@Configuration
@EntityScan(basePackages = {"com.github.taosoft.bookstore"})
public class JPAConfiguration {
}
