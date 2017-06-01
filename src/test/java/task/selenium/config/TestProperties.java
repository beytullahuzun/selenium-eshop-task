package task.selenium.config;

import java.util.Properties;

public class TestProperties {
    public static final String PAGE;
    public static final String BROWSER;
    public static final String DRIVER_PATH;
    public static final String SCREENSHOT_PATH;
    public static final int DEFAULT_WAIT_TIMEOUT;

    static {
        Properties prop = new PropertiesLoader().load();
        PAGE = prop.getProperty("webPage");
        BROWSER = prop.getProperty("browser", "chrome");
        DRIVER_PATH = prop.getProperty("pathToDriver");
        SCREENSHOT_PATH = prop.getProperty("screenshotPath");
        DEFAULT_WAIT_TIMEOUT = Integer.valueOf(prop.getProperty("defaultTimeout", "15"));
    }
}
