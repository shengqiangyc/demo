package com.volunteer.demo.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


@Configuration
@EnableCaching
@EnableConfigurationProperties(RedisProperties.class)
public class RedisConfig extends CachingConfigurerSupport {
    Logger logger = LoggerFactory.getLogger(RedisConfig.class);

    @Autowired
    private RedisProperties redisProperties;

    /**
     * jedis相关配置
     */
    @Bean
    public JedisPool redisPoolFactory() {
        //从yml文件中读取redis的配置
        String host = redisProperties.getHost();
        int port = redisProperties.getPort();
        String password = redisProperties.getPassword();
        int maxIdle = redisProperties.getMaxIdle();
        Long maxWaitMillis = redisProperties.getMaxWaitMillis();
        int timeout = redisProperties.getTimeout();

        logger.info("JedisPool注入成功！！");
        logger.info("redis地址：" + host + ":" + port + "_maxIdle:" + maxIdle + "_maxWaitMillis:" + maxWaitMillis + "_timeout:" + timeout);
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);

        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password);

        return jedisPool;
    }
}
