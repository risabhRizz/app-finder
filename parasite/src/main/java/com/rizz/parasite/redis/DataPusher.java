package com.rizz.parasite.redis;

import com.rizz.parasite.process.ProcessGatherer;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import redis.clients.jedis.Jedis;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

public class DataPusher {
    public static final Logger logger = LogManager.getLogger(DataPusher.class);

    private final String hostname;
    private final long pid;
    private final ProcessGatherer gatherer;
    private final Jedis redis;


    public DataPusher() {
        this.hostname = getHostName();
        this.pid = ProcessHandle.current().pid();
        this.gatherer = new ProcessGatherer();
        this.redis = RedisConnection.getRedis();
    }

    private String getHostName() {
        String hostname = "NO_HOSTNAME_AVAILABLE";
        try {
            hostname = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return hostname;
    }

    public void pushDataToRedis() {
        String hostKey = hostname + ":" + pid;
        Map<String, String> processMap = gatherer.findAllProcesses();
        logger.info("Pushing process-wise data into redis-server: Key: " + hostKey
                + ", Size: " + processMap.size());

        redis.hset(hostKey, processMap);
    }
}
