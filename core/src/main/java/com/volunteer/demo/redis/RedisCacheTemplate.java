/**
 * AllrightsReserved,DesignedBywww.maihaoche.com
 * Package com.volunteer.demo.redis
 * author: shengqiang (shengqiang@maihaoche.com)
 * date:  2018/4/8下午1:53
 * Copyright:2017-2018www.maihaoche.comInc.Allrightsreserved.
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.volunteer.demo.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Description: ${TODO}
 * author: shengqiang (shengqiang@maihaoche.com)
 * date: 2018/4/8下午1:53
 * sinceV1.0
 */
@Component
public class RedisCacheTemplate {

    @Autowired
    private JedisPool jedisPool;

    //获取jedis连接
    private Jedis getJedis (){
        Jedis jedis = jedisPool.getResource();
        if (jedis == null) {
            throw new RuntimeException("Redis 连接有问题啦！");
        }
        return jedis;
    }

    public String getString(String key) {
        Jedis jedis = getJedis();
        String value = jedis.get(key);
        jedis.close();
        return value;
    }

    public boolean setString(String key, String value, int second) {
        Jedis jedis = getJedis();
        String result = jedis.setex(key, second, value);
        jedis.close();
        if (result != null && "OK".equals(result)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public boolean delString(String key){
        Jedis jedis = getJedis();
        jedis.del(key);
        jedis.close();
        return true;
    }
}
