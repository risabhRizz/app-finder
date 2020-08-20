package com.rizz.appfinder.redis;

import com.rizz.appfinder.App;
import com.rizz.appfinder.AppResults;
import com.rizz.appfinder.ConfigLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DataFetcher {
    private static final String PARASITE_SERVER_KEYS = "parasite-server-keys";
    private static final Logger logger = LogManager.getLogger(DataFetcher.class);
    private final Jedis redis;

    public DataFetcher() {
        this.redis = RedisConnection.getRedis();
    }

    public Set<String> fetchParasiteKeys() {
        return redis.smembers(PARASITE_SERVER_KEYS);
    }

    public void populateResults(AppResults results, String name) {

        Set<String> keys = fetchParasiteKeys();
        for (String parasiteKey : keys) { // Parasite Id: Ip address of parasite server
            Map<String, String> allProcess = RedisConnection.getRedis().hgetAll(parasiteKey);

            List<App> appList = new ArrayList<>();
            for (Map.Entry<String, String> processEntry : allProcess.entrySet()) {
                String appStr = processEntry.getValue();
                addAppToList(appStr, appList);
            }

            results.getAppResults().put(parasiteKey, appList);
        }


    }

    private void addAppToList(String appStr, List<App> appList) {
        try {
            String[] appParts = appStr.split(" ");
            App app = new App(Integer.parseInt(appParts[0]), appParts[1],
                    ConfigLoader.getConfig().getServerIp(), appParts[2]);
            appList.add(app);
        } catch (NumberFormatException e) {
            logger.warn("Exception in adding App to list: {" + appStr + "}: " + e.getMessage());
        }
    }
}
