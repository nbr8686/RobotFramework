package com.trysol.core;

import com.trysol.utils.ProcessUtil;
import io.appium.java_client.mac.Mac2Driver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;
import java.time.Duration;

public class MacDriverImpl extends DesktopDriver {
    @Override
    public void startApp(String appBundleId) throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("app", appBundleId);
        caps.setCapability("platformName", "Mac");
        caps.setCapability("automationName", "Mac2");
        caps.setCapability("waitForQuiescence", false);
        caps.setCapability("newCommandTimeout", 300);

        try {
            this.driver = new Mac2Driver(
                    new URL("http://127.0.0.1:4723"),
                    caps);
            this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        } catch (Exception e) {
            throw new RuntimeException("Failed to start Mac application: " + appBundleId +
                    "\n1. Verify Appium is running\n2. Check Accessibility permissions", e);
        }
    }

    @Override
    public void closeApp() throws Exception {
        if (this.driver != null) {
            try {
                this.driver.quit();
            } finally {
                ProcessUtil.killProcess("Calculator");
                ProcessUtil.killProcess("appium");
            }
        }
    }
}