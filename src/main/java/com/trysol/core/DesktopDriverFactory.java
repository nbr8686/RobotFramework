package com.trysol.core;


import com.trysol.utils.PlatformUtils;
import com.trysol.utils.ProcessUtil;

import java.io.IOException;

public class DesktopDriverFactory {
    public static DesktopDriver createDriver() {
        if (PlatformUtils.isWindows()) {
            return new WindowsDriverImpl();
        } else if (PlatformUtils.isMac()) {
            return new MacDriverImpl();
        }
        throw new UnsupportedOperationException(
                "Unsupported platform: " + System.getProperty("os.name") +
                        "\nSupported platforms: Windows 10/11, macOS 10.15+");
    }

    public static void verifyDriverPreconditions() throws IOException {
        if (PlatformUtils.isWindows()) {
            if (!ProcessUtil.isProcessRunning("WinAppDriver.exe")) {
                throw new IllegalStateException("WinAppDriver not running. Please start it first.");
            }
        } else if (PlatformUtils.isMac()) {
            if (!ProcessUtil.isProcessRunning("appium")) {
                throw new IllegalStateException("Appium server not running. Please start it first.");
            }
        }
    }
}