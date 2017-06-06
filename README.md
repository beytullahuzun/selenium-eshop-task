### Automated Selenium tests for e-shop [automationpractice.com](http://automationpractice.com)

#### Supported operating systems
- Linux
- Windows

#### Supported browsers
- Chrome
- Phantomjs

To set browser change `browser` property under _$projectDir/src/test/resources/props/test.properties_.
By default **Phantomjs** is used. 

### Getting webdriver

Downloading proper webdrivers is handled by specific Gradle task named `downloadDrivers`. This gradle task downloads drivers to `/src/test/resources/driver` folder.
> Note: in IDE it doesn't happen automatically, so on the first run you need to execute this Gradle task either from IDE or command line using the following command
```bash
./gradlew clean downloadDrivers
```

### Tests execution

Tests can be executed either from your IDE or from command line using Gradle. Run the following command 

- _for Unix machines_
```bash
./gradlew clean executeCucumberFeatures
```
- _for Windows machines_
```bash
gradlew.bat clean executeCucumberFeatures
```
> Note: if you run tests from command line, no need to run _downloadDrivers_ task as _executeCucumberFeatures_ depends on downloading the drivers