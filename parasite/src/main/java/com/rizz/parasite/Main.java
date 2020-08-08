package com.rizz.parasite;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("All process: " + findAllProcesses());

    }

    public static List<App> findAllProcesses() {
        String command = "ps -eo pid,comm,cmd";

        List<App> appList = new ArrayList<>();

        String s;
        Process p;

        try {
            p = Runtime.getRuntime().exec(command);
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((s = br.readLine()) != null) {
                s = s.trim();
                if (!s.isEmpty() && !s.endsWith("]")) {
                    App app = createApp(s);
                    if (app != null) {
                        appList.add(app);
                    }
                }
            }
            p.waitFor();
            p.destroy();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return appList;
    }

    private static App createApp(String s) {
        String[] appParts = s.split("\\s+");
        if (appParts.length < 3) {
            return null;
        }

        String pid = appParts[0];
        String command = appParts[1];
        String appPath = appParts[2];

        if (pid.length() == 0 || command.length() == 0 || appPath.length() == 0) {
            return null;
        }
        return new App(pid, command, appPath);
    }
}
