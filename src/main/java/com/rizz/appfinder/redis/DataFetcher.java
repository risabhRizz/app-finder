package com.rizz.appfinder.redis;

import com.rizz.appfinder.App;
import redis.clients.jedis.Jedis;

import java.util.Set;

public class DataFetcher {
    Jedis redis = RedisConnection.getRedis();
    private static final String PARASITE_SERVER_KEYS = "parasite-server-keys";

    public App findApp(String name) {
        Set<String> keys = fetchParasiteKeys();

        for (String key : keys) {
            System.out.println(redis.hgetAll(key));
        }
        return new App(123, "Fake process", "1.2.3.4", "/hello/world");
    }

    public Set<String> fetchParasiteKeys() {
        return redis.smembers(PARASITE_SERVER_KEYS);
    }
}
