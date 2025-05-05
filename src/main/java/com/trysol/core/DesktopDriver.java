package com.trysol.core;


import com.trysol.utils.OCRUtil;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class DesktopDriver {
    protected AppiumDriver driver;
    protected final OCRUtil ocr = new OCRUtil();

    public abstract void startApp(String appId) throws Exception;
    public abstract void closeApp() throws Exception;

    public AppiumDriver getDriver() {
        return this.driver;
    }

    public BufferedImage captureScreenRegion(Rectangle region) throws IOException {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        BufferedImage fullImage = ImageIO.read(screenshot);
        return fullImage.getSubimage(
                region.x,
                region.y,
                Math.min(region.width, fullImage.getWidth() - region.x),
                Math.min(region.height, fullImage.getHeight() - region.y)
        );
    }

    public String extractTextFromRegion(Rectangle region) throws Exception {
        return ocr.extractTextFromImage(captureScreenRegion(region));
    }

    public boolean isAppRunning() {
        return driver != null;
    }
}