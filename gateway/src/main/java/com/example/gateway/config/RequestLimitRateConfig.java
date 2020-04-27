package com.example.gateway.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * @author lyy
 * @date 2020/4/26 5:38 下午
 */
@SpringBootConfiguration
public class RequestLimitRateConfig {

    /**
     * 用户限流
     * 通用用户Id做为key来限流,请求路径中必须携带userId参数
     * @return org.springframework.cloud.gateway.filter.ratelimit.KeyResolver
     */
    @Bean
    KeyResolver userKeyResolver() {
        return exchange -> Mono.just(Objects.requireNonNull(exchange.getRequest().getQueryParams().getFirst("userId")));
    }

    /**
     * 接口限流
     * 获取请求地址的uri作为限流key。
     * @return org.springframework.cloud.gateway.filter.ratelimit.KeyResolver
     */
    @Bean
    @Primary
    KeyResolver apiKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getPath().value());
    }
}
