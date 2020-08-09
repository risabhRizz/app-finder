package com.rizz.parasite.redis;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import redis.clients.jedis.Jedis;

public class RedisConnection {
    public static final Logger logger = LogManager.getLogger(RedisConnection.class);
    private static Jedis redis;

    private RedisConnection() {
    }


    public static Jedis getRedis() {
        try {
            if (redis == null) {
                redis = new Jedis("localhost");
            }
        } catch (Exception e) {
            logger.error("Exception occurred in connecting to Redis server: \n" + ExceptionUtils.getStackTrace(e));
        }
        return redis;
    }

}
