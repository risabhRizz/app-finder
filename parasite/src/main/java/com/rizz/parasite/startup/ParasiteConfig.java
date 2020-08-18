package com.rizz.parasite.startup;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.List;

public class ParasiteConfig {
    private static final Logger logger = LogManager.getLogger(ParasiteConfig.class);
    private static ParasiteConfig config;
    private String serverIp;
    private long fetchPeriod;
    private List<String> redisClusterIps;

    public static void loadConfig() {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            config = mapper.readValue(new File("parasite/src/main/resources/parasiteConfig.yaml"),
                    ParasiteConfig.class);
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
    }

    public static ParasiteConfig getConfig() {
        return config;
    }

    public String getServerIp() {
        return serverIp;
    }

    public long getFetchPeriod() {
        return fetchPeriod;
    }

    public List<String> getRedisClusterIps() {
        return redisClusterIps;
    }
}
