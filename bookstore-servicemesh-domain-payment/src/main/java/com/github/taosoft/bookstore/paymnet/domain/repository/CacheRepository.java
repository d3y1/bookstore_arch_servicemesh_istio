package com.github.taosoft.bookstore.paymnet.domain.repository;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.Cache;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

import static com.github.taosoft.bookstore.infrastructure.cache.CacheConfiguration.SYSTEM_DEFAULT_EXPIRES;

/**
 * 业务过程中使用到缓存的数据仓库
 **/
@Configuration
public class CacheRepository {
    @Bean(name = "settlement")
    public Cache getSettlementTTLCache() {
        return new CaffeineCache("settlement", Caffeine.newBuilder().expireAfterAccess(SYSTEM_DEFAULT_EXPIRES, TimeUnit.MILLISECONDS).build());
    }
}
