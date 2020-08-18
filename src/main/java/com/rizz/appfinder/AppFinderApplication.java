package com.rizz.appfinder;

import com.rizz.appfinder.redis.RedisConnection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppFinderApplication {

    public static void main(String[] args) {
        ConfigLoader.loadConfig();
        RedisConnection.createRedisConnection();
        System.out.println("Redis keys: " + RedisConnection.getRedis().keys("*"));
        SpringApplication.run(AppFinderApplication.class, args);
    }

}
