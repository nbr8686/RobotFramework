package com.trysol.tests;

import com.trysol.apps.calculator.CalculatorPage;
import com.trysol.core.DesktopDriver;
import com.trysol.core.DesktopDriverFactory;
import com.trysol.elements.Window;
import com.trysol.utils.Config;
import org.testng.annotations.*;
import static org.testng.Assert.*;

public class CalculatorTests {
    private DesktopDriver driver;
    private CalculatorPage calculator;

    @BeforeClass
    public void setup() throws Exception {
        driver = DesktopDriverFactory.createDriver();
        driver.startApp(Config.getProperty("calculator.app"));
        calculator = new CalculatorPage(new Window(driver.getDriver()));
    }

    @Test
    public void testAddition() {
        calculator.clickNumber(5);
        calculator.clickOperator("+");
        calculator.clickNumber(3);
        assertEquals(calculator.getResult(), "8");
    }

    @AfterClass
    public void teardown() throws Exception {
        driver.closeApp();
    }
}