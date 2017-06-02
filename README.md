### Automated Selenium tests for e-shop [automationpractice.com](http://automationpractice.com)

#### Supported operating systems
- Linux
- Windows

#### Supported browsers
- Chrome
- Phantomjs

To set browser change `browser` property under _$projectDir/src/test/resources/props/test.properties_.
By default **Phantomjs** is used. 

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
