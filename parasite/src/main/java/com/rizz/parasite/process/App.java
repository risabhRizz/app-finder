package com.rizz.parasite.process;

public class App {
    private final String processId;
    private final String command;
    private final String absPath;

    public App(String processId, String command, String absPath) {
        this.processId = processId;
        this.command = command;
        this.absPath = absPath;
    }

    public String getProcessId() {
        return processId;
    }

    public String getCommand() {
        return command;
    }

    public String getAbsPath() {
        return absPath;
    }

    @Override
    public String toString() {
        return "App{" +
                "processId=" + processId +
                ", command='" + command + '\'' +
                ", absPath='" + absPath + '\'' +
                '}';
    }
}
