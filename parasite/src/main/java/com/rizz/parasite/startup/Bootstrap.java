package com.rizz.parasite.startup;

import com.rizz.parasite.redis.DataPusherTask;
import com.rizz.parasite.redis.RedisConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Timer;

public class Bootstrap {
    private static final Logger logger = LogManager.getLogger(Bootstrap.class);

    public static void main(String[] args) {
        ParasiteConfig.loadConfig();
        boolean isConnected = RedisConnection.createRedisConnection();
        logger.info("Redis connection status: " + isConnected);
        if (isConnected) {
            DataPusherTask dataPusherTask = new DataPusherTask();
            Timer scheduler = new Timer();
            scheduler.schedule(dataPusherTask, 0, ParasiteConfig.getConfig().getFetchPeriod());
        }
    }
}
