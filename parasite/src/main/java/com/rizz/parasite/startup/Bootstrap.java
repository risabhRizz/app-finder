package com.rizz.parasite.startup;

import com.rizz.parasite.redis.DataPusher;

public class Bootstrap {
    public static void main(String[] args) {
        DataPusher dataPusher = new DataPusher();
        dataPusher.pushDataToRedis();
    }
}
