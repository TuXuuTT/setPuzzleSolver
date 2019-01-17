package com.automation;

import com.automation.browserClient.BrowserClient;
import com.automation.environment.EnvironmentConfigurator;
import com.automation.logger.Logger;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import io.qameta.allure.Attachment;
import lombok.Getter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.net.MalformedURLException;

public abstract class BasicTest extends AbstractTestNGCucumberTests implements IHookable {

    @Getter
    private static RemoteWebDriver wd;

    @BeforeClass(alwaysRun = true)
    public void startUp() {
        try {
            wd = new BrowserClient().getDriver(EnvironmentConfigurator.getInstance().getBrowserClient());
        } catch (MalformedURLException e) {
            e.printStackTrace();
            Logger.out.error("Error during driver start up", e);
        }
    }

    @AfterClass(alwaysRun = true)
    public void tierDown() {
        if (wd != null) {
            wd.quit();
        }
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuiteStop() {
    }

    @Override
    public void run(IHookCallBack callBack, ITestResult testResult) {
        callBack.runTestMethod(testResult);
        if (testResult.getThrowable() != null) {
            try {
                takeScreenShot(testResult.getMethod().getMethodName());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Attachment(value = "Failure in method {0}", type = "image/jpeg")
    private byte[] takeScreenShot(String failureReason) throws IOException {
        Logger.out.info(String.format("Taking screenshot due to fail in method %s", failureReason));
        return ((TakesScreenshot) wd).getScreenshotAs(OutputType.BYTES);
    }
}