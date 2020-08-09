package com.rizz.parasite.redis;

import com.rizz.parasite.process.App;
import com.rizz.parasite.process.ProcessGatherer;

import java.util.List;

public class DataPusher {
    private final ProcessGatherer gatherer;

    public DataPusher() {
        this.gatherer = new ProcessGatherer();
    }

    public void pushDataToRedis() {
        List<App> appList = gatherer.findAllProcesses();

        for (App application : appList) {
            RedisConnection.getRedis().lpush("localhost1:1234", application.getCommand());
        }
    }
}
