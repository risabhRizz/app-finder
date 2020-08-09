package com.rizz.parasite.redis;

import com.rizz.parasite.process.ProcessGatherer;
import redis.clients.jedis.Jedis;

import java.util.Map;

public class DataPusher {
    private final ProcessGatherer gatherer;
    private final Jedis redis;

    public DataPusher() {
        this.gatherer = new ProcessGatherer();
        this.redis = RedisConnection.getRedis();
    }

    public void pushDataToRedis() {
        Map<String, String> processMap = gatherer.findAllProcesses();
        redis.hset("10.0.0.1:8976", processMap);
    }
}
