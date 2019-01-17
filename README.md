The project is written using _Java + Selenium(Selenide) + Cucumber(BDD)_ stack.
Test code is self-documented, trying to follow best practices of usage of PageObject pattern. Although 'Selenide' library makes it even more simple. It should be easy to read existing code and extend it to increase test coverage.

Test Scenarios cover most major part of functionality and verifications of critical functions. There are few bugs were found by brief overview which were not covered by these tests like presence of "sound off" button in html code, but absence on the page and like presence of "lifetime wins and day wins" only on one of the slot machines.

To simply run test suite just execute maven goal:

`mvn clean compile`
and
`mvn test`

If running from IDE, please use for your run configuration _testng_dflt.xml_ suite file or _TestSetPuzzleSmokeTestRunner_ from _"com.automation.testCucmbrRunners.web"_ folder.

Do not use direct run configuration against feature file, because it will not apply predefined parameters for webDriver launch from _BasicTest.java_ class.
In any case, one runner corresponds exactly to one feature file, so this should not make any inconvenience :)

#### Notes:
This implementation was tested on Windows OS: chromedriver 2.33 and Chrome browser 62. Newer versions of browsers may cause issues with WebDriver.

### IMPORTANT
The latest chromedriver used may be inconsistent and may failure to run from first launch for unexpected reasons and fail with "Unable to start ChromeDriver" exception.
Relaunching test suite usually helps and tests are executed in expected way.
This issue is definitely technically emerges through first compilation stage so might be fixed in real life scenarios but needs time for investigating and not critical for our purposes.

Thanks