package com.trysol.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Component {
    protected final Window window;
    protected final By locator;

    public Component(Window window, By locator) {
        this.window = window;
        this.locator = locator;
    }

    public void click() {
        window.click(locator);
    }

    public String getText() {
        return window.getText(locator);
    }

    public boolean isDisplayed() {
        return window.isDisplayed(locator);
    }
}