package task.selenium.driver;

import com.google.common.base.Throwables;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import static java.util.concurrent.TimeUnit.SECONDS;
import static task.selenium.config.TestProperties.*;

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
                case FIREFOX:
                    driver = getFirefoxDriver();
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
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, getDriverAbsolutePath());
        return new PhantomJSDriver(caps);
    }

    private static WebDriver getFirefoxDriver() {
        ProfilesIni profile = new ProfilesIni();
        FirefoxProfile myprofile = profile.getProfile("default");
        return new FirefoxDriver(myprofile);
    }

    private static WebDriver getChromeDriver() {
        System.setProperty("webdriver.chrome.driver", getDriverAbsolutePath());
        return new ChromeDriver();
    }

    private static String getDriverAbsolutePath() {
        URL url = DriverManager.class.getResource(DRIVER_PATH);
        File file = null;
        try {
            file = new File(url.toURI());
        } catch (URISyntaxException e) {
            Throwables.propagate(e);
        }
        return file.getAbsolutePath();
    }
}
