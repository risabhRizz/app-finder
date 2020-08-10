package com.rizz.parasite.startup;

import com.rizz.parasite.redis.DataPusherTask;
import com.rizz.parasite.redis.RedisConnection;

import java.util.Timer;

public class Bootstrap {
    public static void main(String[] args) {
        boolean isConnected = RedisConnection.createRedisConnection();
        if (isConnected) {
            DataPusherTask dataPusherTask = new DataPusherTask();
            Timer scheduler = new Timer();
            scheduler.schedule(dataPusherTask, 0, 5000);
        }
    }
}
