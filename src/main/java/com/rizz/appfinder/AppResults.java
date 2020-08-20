package com.rizz.appfinder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppResults {
    private final Map<String, List<App>> appResults;

    public AppResults() {
        this.appResults = new HashMap<>();
    }

    public Map<String, List<App>> getAppResults() {
        return appResults;
    }
}
