package com.rizz.appfinder;

public class App {
    private final int processId;
    private final String name;
    private final String serverIp;
    private final String absPath;

    public App(int processId, String name, String serverIp, String absPath) {
        this.processId = processId;
        this.name = name;
        this.serverIp = serverIp;
        this.absPath = absPath;
    }

    public int getProcessId() {
        return processId;
    }

    public String getName() {
        return name;
    }

    public String getServerIp() {
        return serverIp;
    }

    public String getAbsPath() {
        return absPath;
    }
}
