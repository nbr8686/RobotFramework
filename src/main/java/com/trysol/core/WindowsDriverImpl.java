package com.trysol.core;

import com.trysol.utils.ProcessUtil;
import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;
import java.time.Duration;

public class WindowsDriverImpl extends DesktopDriver {
    @Override
    public void startApp(String appId) throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("app", appId);
        caps.setCapability("platformName", "Windows");
        caps.setCapability("deviceName", "WindowsPC");
        caps.setCapability("ms:waitForAppLaunch", "25");
        caps.setCapability("newCommandTimeout", 300);

        try {
            this.driver = new WindowsDriver(
                    new URL("http://127.0.0.1:4723"),
                    caps
            );
            this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        } catch (Exception e) {
            throw new RuntimeException("Failed to start Windows application: " + appId +
                    "\n1. Verify WinAppDriver is running\n2. Check app ID is correct", e);
        }
    }

    @Override
    public void closeApp() throws Exception {
        if (this.driver != null) {
            try {
                this.driver.quit();
            } finally {
                ProcessUtil.killProcess("Calculator.exe");
                ProcessUtil.killProcess("WinAppDriver.exe");
            }
        }
    }
}