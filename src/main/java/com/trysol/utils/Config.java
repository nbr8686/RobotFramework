package com.trysol.utils;

import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final Properties props = new Properties();

    static {
        loadConfig();
    }

    private static void loadConfig() {
        String platform = PlatformUtils.getPlatformPrefix();
        String configFile = "config/" + platform + ".properties";

        try (InputStream input = Config.class.getClassLoader().getResourceAsStream(configFile)) {
            if (input == null) {
                throw new RuntimeException("Config file not found: " + configFile +
                        "\nMake sure the file exists in src/main/resources/config/");
            }
            props.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config file: " + configFile, e);
        }
    }

    public static String getProperty(String key) {
        String value = props.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Property '" + key + "' not found in config");
        }
        return value;
    }
}