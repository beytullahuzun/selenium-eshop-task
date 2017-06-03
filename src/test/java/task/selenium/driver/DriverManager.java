package task.selenium.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.openqa.selenium.phantomjs.PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY;
import static task.selenium.config.TestProperties.BROWSER;
import static task.selenium.config.TestProperties.DEFAULT_WAIT_TIMEOUT;

public class DriverManager {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            switch (SupportedBrowser.valueOf(BROWSER.toUpperCase())) {
                case PHANTOMJS:
                    driver = getPhantomjsDriver();
                    break;
                case CHROME:
                    driver = getChromeDriver();
                    break;
            }
            driver.manage().timeouts().implicitlyWait(DEFAULT_WAIT_TIMEOUT, SECONDS);
            driver.manage().deleteAllCookies();
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void closeDriver() {
        driver.quit();
        driver = null;
    }

    private static WebDriver getPhantomjsDriver() {
        DesiredCapabilities caps = new DesiredCapabilities();
        String driverPath = DriverPath.getPhantomjsPath();
        caps.setCapability(PHANTOMJS_EXECUTABLE_PATH_PROPERTY, driverPath);
        return new PhantomJSDriver(caps);
    }

    private static WebDriver getChromeDriver() {
        String driverPath = DriverPath.getChromeDriverPath();
        System.setProperty("webdriver.chrome.driver", driverPath);
        return new ChromeDriver();
    }
}
