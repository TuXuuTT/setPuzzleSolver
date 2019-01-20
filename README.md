The project is written using _Java + Pure Selenium + Cucumber(BDD)_ stack.
Test code is self-documented, trying to follow best practices of usage of PageObject pattern.

Test Scenarios covers opening puzzle page, solving puzzle and verifying user is redirected to "Completed" page.

To simply run test suite just execute maven goal:

`mvn clean compile`
and
`mvn test`

If running from IDE, please use for your run configuration _testng_dflt.xml_ suite file or _TestSetPuzzleSmokeTestRunner_ from _"com.automation.testCucmbrRunners"_ folder.

Do not use direct run configuration against feature file, because it will not apply predefined parameters for webDriver launch from _BasicTest.java_ class.
In any case, one runner corresponds exactly to one feature file, so this should not make any inconvenience.

#### Notes:
This implementation was tested on Windows OS: chromedriver  2.45 and Chrome browser 71.0. Newer versions of browsers may cause issues with WebDriver.

Thanks
