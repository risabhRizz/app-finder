package com.rizz.parasite.redis;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import redis.clients.jedis.Jedis;

public class RedisConnection {
    private static final Logger logger = LogManager.getLogger(RedisConnection.class);
    private static Jedis redis;

    private RedisConnection() {
    }

    public static boolean createRedisConnection() {
        try {
            if (redis == null) {
                redis = new Jedis("localhost");
            }
            redis.randomKey();
        } catch (Exception e) {
            logger.error("Error occurred while creating Redis connection.\n"
                    + ExceptionUtils.getStackTrace(e));
            return false;
        }

        return true;
    }

    public static Jedis getRedis() {
        return redis;
    }

}
