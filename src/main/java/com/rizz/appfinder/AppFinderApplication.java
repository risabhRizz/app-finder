package com.rizz.appfinder;

import com.rizz.appfinder.redis.RedisConnection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.RedisConnectionFailureException;

@SpringBootApplication
public class AppFinderApplication {

    public static void main(String[] args) {
        ConfigLoader.loadConfig();
        boolean connectStatus = RedisConnection.createRedisConnection();
        if (!connectStatus) {
            throw new RedisConnectionFailureException("Could not make a connection with Redis-server");
        }
        SpringApplication.run(AppFinderApplication.class, args);
    }

}
