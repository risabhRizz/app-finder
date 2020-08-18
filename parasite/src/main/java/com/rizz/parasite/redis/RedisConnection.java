package com.rizz.parasite.redis;

import com.rizz.parasite.startup.ParasiteConfig;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import redis.clients.jedis.Jedis;

public class RedisConnection {
    private static final Logger logger = LogManager.getLogger(RedisConnection.class);
    private static final String PARASITE_SERVER_KEYS = "parasite-server-keys";
    private static Jedis redis;

    private RedisConnection() {
    }

    public static boolean createRedisConnection() {
        logger.info("Creating connection with Redis server");
        try {
            if (redis == null) {
                redis = new Jedis(ParasiteConfig.getConfig().getServerIp());
            }

            logger.info("Pushing Parasite-key: [" + ParasiteConfig.getConfig().getServerIp() + "]");
            redis.sadd(PARASITE_SERVER_KEYS, ParasiteConfig.getConfig().getServerIp());
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
