package com.jack.gumballs.config.redis;

import com.jack.gumballs.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by LiJiakui on 17/4/25.
 */
//@Configuration
public class RedisConfiguration {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.timeout}")
    private int timeout;

    @Value("${spring.redis.pool.max-idle}")
    private int maxIdle;

    @Value("${spring.redis.pool.max-wait}")
    private long maxWait;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.pool.max-active}")
    private int maxActive;

    @Value("${spring.redis.database}")
    private int database;
    @Bean
    public JedisPool jedisPool() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWait(maxWait);
        jedisPoolConfig.setMaxActive(maxActive);
        jedisPoolConfig.setTestOnBorrow(true);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password,database);
        return jedisPool;
    }

    @Bean
    public RedisUtil redisClient(@Qualifier("jedisPool") JedisPool jedisPool){
        RedisUtil redisClient = new RedisUtil();
        redisClient.setJedisPool(jedisPool);
        return redisClient;
    }
}
