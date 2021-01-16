package com.puruan.redis;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Configuration
@AutoConfigureBefore({RedisAutoConfiguration.class})
@EnableConfigurationProperties({RedisProperties.class})
public class RedisConfiguration extends CachingConfigurerSupport {

    private static final int DEFAULT_REDIS_KEY_DURATION = 10; // Minutes

    @Bean
    @ConditionalOnMissingBean(name = "redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(
            final RedisConnectionFactory redisConnectionFactory) {
        final RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        return template;
    }

    @Bean
    CacheManager cacheManager(final RedisConnectionFactory redisConnectionFactory) {
        // global configuration
        RedisCacheConfiguration config =
                RedisCacheConfiguration.defaultCacheConfig()
                        .entryTtl(Duration.ofMinutes(DEFAULT_REDIS_KEY_DURATION))
                        .disableKeyPrefix()
                        .disableCachingNullValues();

        // specific keys configuration
        Set<String> cacheNames = new HashSet<>();
        cacheNames.add("");

        Map<String, RedisCacheConfiguration> configMap = new HashMap<>();
        configMap.put("", config.entryTtl(Duration.ofDays(1)));

        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(config)
                // pre initialized with specific keys
                .initialCacheNames(cacheNames)
                .withInitialCacheConfigurations(configMap)
                .
                // build
                        transactionAware()
                .build();
    }
}
