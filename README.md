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

_on Linux_
```bash
./gradlew clean downloadDrivers
```
_on Windows_
```bash
gradlew.bat clean downloadDrivers
```
### Cucumber test framework

[Cucumber](https://cucumber.io/) was chosen as a testing tool. It supports Behavior Driven Development (BDD) framework. The reason behind that is that Cucumber defines application behavior using simple English text which describes user behaviour.


### Tests execution

Tests can be executed either from your IDE or from command line using Gradle. 
1. To run a tests from IntelliJ IDEA get to `.feature` file under _test/resources/features_ and run a separate test scenario or the whole suite. To do that just right click on a **Scenario** or a **Feature** and choose "Run 'Scenario ...''"/ "Run 'Feature ...''"

2. To run test from command line use the following Gradle command

- _for Linux machines_
```bash
./gradlew clean executeCucumberFeatures
```
- _for Windows machines_
```bash
gradlew.bat clean executeCucumberFeatures
```
> Note: if you run tests from command line, no need to run _downloadDrivers_ task as _executeCucumberFeatures_ depends on downloading the drivers