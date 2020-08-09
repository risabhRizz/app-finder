package com.rizz.parasite.redis;

import com.rizz.parasite.process.ProcessGatherer;
import com.rizz.parasite.util.ParasiteUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import redis.clients.jedis.Jedis;

import java.util.Map;

public class DataPusher {
    private static final Logger logger = LogManager.getLogger(DataPusher.class);

    private final ProcessGatherer gatherer;
    private final Jedis redis;


    public DataPusher() {
        this.gatherer = new ProcessGatherer();
        this.redis = RedisConnection.getRedis();
    }


    public void pushDataToRedis() {
        String hostKey = ParasiteUtility.getHostName();
        Map<String, String> processMap = gatherer.findAllProcesses();
        logger.info("Pushing process-wise data into redis-server: Key: " + hostKey
                + ", Size: " + processMap.size());

        redis.hset(hostKey, processMap);
    }
}
