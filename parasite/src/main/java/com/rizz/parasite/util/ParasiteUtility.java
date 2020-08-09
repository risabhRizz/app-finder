package com.rizz.parasite.util;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ParasiteUtility {
    private static final Logger logger = LogManager.getLogger(ParasiteUtility.class);

    public static String getHostName() {
        String hostname = "NO_HOSTNAME_AVAILABLE";
        try {
            hostname = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return hostname;
    }
}
