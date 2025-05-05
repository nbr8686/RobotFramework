package com.trysol.utils;

public class PlatformUtils {
    public static boolean isWindows() {
        String os = System.getProperty("os.name", "").toLowerCase();
        return os.contains("win");
    }

    public static boolean isMac() {
        String os = System.getProperty("os.name", "").toLowerCase();
        return os.contains("mac");
    }

    public static String getPlatformPrefix() {
        if (isWindows()) return "windows";
        if (isMac()) return "mac";
        throw new UnsupportedOperationException(
                "Unsupported platform: " + System.getProperty("os.name") +
                        "\nSupported platforms: Windows, macOS");
    }
}