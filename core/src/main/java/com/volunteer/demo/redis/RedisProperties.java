package com.volunteer.demo.redis;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "spring.redis")
public class RedisProperties {
    private String host;
    private int port;
    private String password;
    private int timeout;
    private int maxIdle;
    private long maxWaitMillis;
}
