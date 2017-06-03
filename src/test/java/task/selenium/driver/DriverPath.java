package task.selenium.driver;

import java.util.stream.Stream;

public enum DriverPath {

    LINUX("linux", "/driver/phantomjs", "/driver/chromedriver"),
    WINDOWS("windows", "/driver/phantomjs.exe", "/driver/chromedriver.exe");

    private final String osDescriptor;
    private final String phantomjsPath;
    private final String chromedriverPath;

    DriverPath(String osDescriptor, String phantomjsPath, String chromedriverPath) {
        this.osDescriptor = osDescriptor;
        this.phantomjsPath = phantomjsPath;
        this.chromedriverPath = chromedriverPath;
    }

    public static String getChromeDriverPath() {
        return getAbsolutePath(getOs().chromedriverPath);
    }

    public static String getPhantomjsPath() {
        return getAbsolutePath(getOs().phantomjsPath);
    }

    private static DriverPath getOs() {
        String os = System.getProperty("os.name").toLowerCase().split("\\s+")[0];

        return Stream.of(DriverPath.values())
                .filter(e -> os.equals(e.osDescriptor))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Unsupported operating system " + os));
    }

    private static String getAbsolutePath(String path) {
        return DriverPath.class.getResource(path).getPath();
    }
}