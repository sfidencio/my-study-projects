package com.github.sfidencio.vendas.infra.config.cache;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;

@Configuration
@EnableRedisRepositories
public class RedisConfig {
    @Value("${myapp.cache.ttl:60}")
    private long ttl;

    @Bean
    public RedisCacheConfiguration cacheConfiguration() {
        return RedisCacheConfiguration
                .defaultCacheConfig()
                .entryTtl(Duration.ofDays(1)) //Aqui TTL global
                .disableCachingNullValues() //desabilita cache de valores nulos
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
    }

    @Bean
    public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
        return builder -> builder
                .withCacheConfiguration("produto", RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(this.ttl)))
                .withCacheConfiguration("cliente", RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(this.ttl)))
                .withCacheConfiguration("pedido", RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(this.ttl)));
    }
}
