package qtriptest;

import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverSingleton {
    private static RemoteWebDriver driver;

    private DriverSingleton() {
        // Private constructor to prevent instantiation
    }

    public static RemoteWebDriver getDriver() {
        if (driver == null) {
            initializeDriver();
        }
        return driver;
    }

    private static void initializeDriver() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(BrowserType.CHROME);

        try {
            // Launch Browser using Zalenium
            driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize WebDriver");
        }
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null; // Set to null after quitting to allow re-initialization
        }
    }
}