### Automated Selenium tests for e-shop [automationpractice.com](automationpractice.com)

To execute tests from command line using Gradle run the following command 

- _for Unix machines_
```bash
./gradlew clean executeCucumberFeatures
```
- _for Windows machines_
```bash
gradlew.bat clean executeCucumberFeatures
```
#### Supported operating systems
- Linux
- Windows

#### Supported browsers
- Chrome
- Phantomjs

To set browser change `browser` property under _$projectDir/src/test/resources/props/test.properties_.
By default **Phantomjs** is used. 