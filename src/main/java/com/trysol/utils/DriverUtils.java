package com.trysol.utils;

import com.trysol.core.DesktopDriver;
import com.trysol.core.DesktopDriverFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class DriverUtils {
    public static void initializeDriver(AtomicReference<DesktopDriver> driverRef) throws Exception {
        DesktopDriverFactory.verifyDriverPreconditions();

        int maxRetries = 3;
        int retryDelaySeconds = 5;

        for (int i = 0; i < maxRetries; i++) {
            try {
                DesktopDriver driver = DesktopDriverFactory.createDriver();
                driver.startApp(Config.getProperty("calculator.app"));
                if (driver.isAppRunning()) {
                    driverRef.set(driver);
                    return;
                }
            } catch (Exception e) {
                if (i == maxRetries - 1) {
                    throw new RuntimeException("Failed to initialize driver after " + maxRetries + " attempts", e);
                }
                TimeUnit.SECONDS.sleep(retryDelaySeconds);
            }
        }
    }

    public static void safeCloseDriver(DesktopDriver driver) {
        if (driver != null) {
            try {
                driver.closeApp();
            } catch (Exception e) {
                System.err.println("Error while closing driver: " + e.getMessage());
            }
        }
    }
}