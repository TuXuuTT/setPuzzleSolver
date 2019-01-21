package com.automation;

import com.automation.browserClient.BrowserClient;
import com.automation.environment.EnvironmentConfigurator;
import com.automation.logger.Logger;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import lombok.Getter;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;

public abstract class BasicTest extends AbstractTestNGCucumberTests {

    @Getter
    private static RemoteWebDriver webDriver;

    @BeforeClass(alwaysRun = true)
    public void startUp() {
        try {
            webDriver = new BrowserClient().getDriver(EnvironmentConfigurator.getInstance().getBrowserClient());
        } catch (MalformedURLException e) {
            e.printStackTrace();
            Logger.out.error("Error during driver start up", e);
        }
    }

    @AfterClass(alwaysRun = true)
    public void tierDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuiteStop() {
    }


}