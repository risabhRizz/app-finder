package com.rizz.parasite.redis;

import redis.clients.jedis.Jedis;

public class RedisConnection {
    private static Jedis redis;

    private RedisConnection() {
    }


    public static Jedis getRedis() {
        if (redis == null) {
            redis = new Jedis("localhost");
        }
        return redis;
    }

}
