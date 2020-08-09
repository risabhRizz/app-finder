package com.rizz.parasite.process;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ProcessGatherer {

    public static final Logger logger = LogManager.getLogger(ProcessGatherer.class);

    public Map<String, String> findAllProcesses() {
        String command = "ps -eo pid,comm,cmd";

        logger.info("Executing linux command to get all running processes: [" + command + "]");

        Map<String, String> processMap = new HashMap<>();

        String s;
        Process p;

        try {
            p = Runtime.getRuntime().exec(command);
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((s = br.readLine()) != null) {
                s = s.trim();
                if (!s.isEmpty() && !s.endsWith("]")) {
                    addProcessToMap(s, processMap);
                }
            }
            p.waitFor();
            p.destroy();
        } catch (IOException | InterruptedException e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }

        return processMap;
    }

    private void addProcessToMap(String s, Map<String, String> processMap) {
        String[] appParts = s.split("\\s+");
        try {
            String pid = appParts[0].trim();
            String command = appParts[1].trim();
            String absolutePath = appParts[2].trim();

            processMap.put(command, pid + " " + command + " " + absolutePath);
        } catch (Exception e) {
            logger.error("Exception occurred while splitting App parts: " + Arrays.toString(appParts));
            logger.error(ExceptionUtils.getStackTrace(e));
        }
    }
}
