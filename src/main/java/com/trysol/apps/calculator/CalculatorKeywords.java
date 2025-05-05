package com.trysol.apps.calculator;

import com.trysol.core.DesktopDriver;
import com.trysol.elements.Window;
import com.trysol.utils.DriverUtils;
import org.robotframework.javalib.annotation.RobotKeyword;
import org.robotframework.javalib.annotation.RobotKeywords;
import java.util.concurrent.atomic.AtomicReference;

@RobotKeywords
public class CalculatorKeywords {
    private static final AtomicReference<DesktopDriver> driverRef = new AtomicReference<>();
    private static CalculatorPage calculator;

    @RobotKeyword("Open Calculator")
    public static void openCalculator() throws Exception {
        DriverUtils.initializeDriver(driverRef);
        calculator = new CalculatorPage(new Window(driverRef.get().getDriver()));

        if (!calculator.verifyCalculatorReady()) {
            throw new RuntimeException("Calculator failed to launch properly");
        }
    }

    @RobotKeyword("Calculate ${expression}")
    public static String calculate(String expression) throws Exception {
        if (calculator == null) {
            throw new IllegalStateException("Calculator not initialized. Call 'Open Calculator' first");
        }

        String[] parts = expression.split("\\s+");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Expression must be in format '5 + 3'");
        }

        calculator.clickNumber(Integer.parseInt(parts[0]));
        calculator.clickOperator(parts[1]);
        calculator.clickNumber(Integer.parseInt(parts[2]));
        return calculator.getResult();
    }

    @RobotKeyword("Close Calculator")
    public static void closeCalculator() {
        DriverUtils.safeCloseDriver(driverRef.get());
        driverRef.set(null);
        calculator = null;
    }
}