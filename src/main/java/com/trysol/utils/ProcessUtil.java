package com.trysol.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProcessUtil {
    public static void killProcess(String processName) throws IOException {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            Runtime.getRuntime().exec("taskkill /F /IM " + processName);
        } else {
            Runtime.getRuntime().exec("pkill -f " + processName);
        }
    }

    public static boolean isProcessRunning(String processName) throws IOException {
        String os = System.getProperty("os.name").toLowerCase();
        String command = os.contains("win")
                ? "tasklist /FI \"IMAGENAME eq " + processName + "\""
                : "ps aux | grep " + processName;

        Process process = Runtime.getRuntime().exec(command);
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(processName)) {
                    return true;
                }
            }
        }
        return false;
    }
}