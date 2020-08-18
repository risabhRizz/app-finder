package com.rizz.parasite.redis;

import com.rizz.parasite.process.ProcessGatherer;
import com.rizz.parasite.startup.ParasiteConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import redis.clients.jedis.Jedis;

import java.util.Map;
import java.util.TimerTask;

public class DataPusherTask extends TimerTask {
    private static final Logger logger = LogManager.getLogger(DataPusherTask.class);

    private final ProcessGatherer gatherer;
    private final Jedis redis;


    public DataPusherTask() {
        this.gatherer = new ProcessGatherer();
        this.redis = RedisConnection.getRedis();
    }

    @Override
    public void run() {
        String hostKey = ParasiteConfig.getConfig().getServerIp();
        Map<String, String> processMap = gatherer.findAllProcesses();
        logger.info("Pushing process-wise data into redis-server: Key: " + hostKey
                + ", Size: " + processMap.size());

        redis.hset(hostKey, processMap);
    }
}
