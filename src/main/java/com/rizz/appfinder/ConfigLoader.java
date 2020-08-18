package com.rizz.appfinder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.List;

public class ConfigLoader {
    private static final Logger logger = LogManager.getLogger(ConfigLoader.class);
    private static ConfigLoader config;
    private String serverIp;
    private List<String> redisClusterIps;

    public static void loadConfig() {
        if (config == null) {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            try {
                config = mapper.readValue(new File("src/main/resources/appFinderConfig.yaml"),
                        ConfigLoader.class);
            } catch (Exception e) {
                logger.error(ExceptionUtils.getStackTrace(e));
            }
        }
    }

    public static ConfigLoader getConfig() {
        return config;
    }

    public String getServerIp() {
        return serverIp;
    }

    public List<String> getRedisClusterIps() {
        return redisClusterIps;
    }
}
