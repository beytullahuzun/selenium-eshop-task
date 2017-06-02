package task.selenium.driver;

import java.util.stream.Stream;

public enum DriverPath {

    UNIX("linux", "/driver/phantomjs", "/driver/chromedriver"),
    WINDOWS("windows", "/driver/phantomjs.exe", "/driver/chromedriver.exe");

    private final String osDescriptor;
    private final String phantomjsPath;
    private final String chromedriverPath;

    DriverPath(String osDescriptor, String phantomjsPath, String chromedriverPath) {
        this.osDescriptor = osDescriptor;
        this.phantomjsPath = phantomjsPath;
        this.chromedriverPath = chromedriverPath;
    }

    private static DriverPath getOs() {
        String os = System.getProperty("os.name").toLowerCase().split("\\s+")[0];

        return Stream.of(DriverPath.values())
                .filter(e -> os.equals(e.osDescriptor))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Unsupported operating system " + os));
    }

    public static String getChromeDriverPath() {
        return getOs().chromedriverPath;
    }

    public static String getPhantomjsPath() {
        return getOs().phantomjsPath;
    }
}