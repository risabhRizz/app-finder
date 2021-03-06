package com.rizz.appfinder.redis;

import com.rizz.appfinder.ConfigLoader;
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
        logger.info("Creating connection with Redis server");
        try {
            if (redis == null) {
                redis = new Jedis(ConfigLoader.getConfig().getServerIp());
            }
            redis.randomKey();
        } catch (Exception e) {
            logger.error("Error occurred while creating Redis connection.\n"
                    + ExceptionUtils.getStackTrace(e));
            return false;
        }

        logger.info("Redis connection successful");
        return true;
    }

    public static Jedis getRedis() {
        return redis;
    }

}
