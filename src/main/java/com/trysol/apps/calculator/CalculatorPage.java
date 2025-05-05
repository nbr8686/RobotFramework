package com.trysol.apps.calculator;


import com.trysol.elements.Window;
import com.trysol.utils.PlatformUtils;
import org.openqa.selenium.By;
import java.util.concurrent.TimeUnit;

public class CalculatorPage {
    private final Window window;
    private final By resultLocator;

    public CalculatorPage(Window window) {
        this.window = window;
        this.resultLocator = PlatformUtils.isWindows()
                ? By.name("Display is")
                : By.xpath("//AXStaticText[contains(@AXValue, '=')]");
    }

    public boolean verifyCalculatorReady() {
        try {
            window.findElement(resultLocator);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void clickNumber(int num) {
        window.click(getNumberLocator(num));
    }

    public void clickOperator(String op) {
        window.click(getOperatorLocator(op));
    }

    public String getResult() {
        String rawText = window.getText(resultLocator);
        return PlatformUtils.isWindows()
                ? rawText.replace("Display is", "").trim()
                : rawText.split("=")[1].trim();
    }

    private By getNumberLocator(int num) {
        if (PlatformUtils.isWindows()) {
            return By.name(String.valueOf(num));
        } else {
            return By.xpath(String.format("//AXButton[@AXTitle='%d']", num));
        }
    }

    private By getOperatorLocator(String op) {
        String operator = op.trim();
        if (PlatformUtils.isWindows()) {
            return By.name(operator.equals("+") ? "Plus" :
                    operator.equals("-") ? "Minus" :
                            operator.equals("*") ? "Multiply" : "Divide");
        } else {
            return By.xpath(String.format("//AXButton[@AXTitle='%s']", operator));
        }
    }
}