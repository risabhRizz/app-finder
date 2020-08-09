package com.rizz.parasite.startup;

import com.rizz.parasite.redis.DataPusher;
import com.rizz.parasite.redis.RedisConnection;

public class Bootstrap {
    public static void main(String[] args) {
        boolean isConnected = RedisConnection.createRedisConnection();
        if (isConnected) {
            DataPusher dataPusher = new DataPusher();
            dataPusher.pushDataToRedis();
        }
    }
}
