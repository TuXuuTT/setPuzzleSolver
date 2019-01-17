package com.automation.browserClient;

import com.automation.environment.EnvironmentConfigurator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.awt.*;
import java.net.MalformedURLException;

import static java.lang.Thread.currentThread;

public class BrowserClient {

    protected static final Logger LOGGER = LogManager.getLogger(BrowserClient.class);
    protected static EnvironmentConfigurator environmentConfigurator;
    private RemoteWebDriver webDriver;

    public BrowserClient() {
        environmentConfigurator = EnvironmentConfigurator.getInstance();
    }

    public static void maximizeWindow(WebDriver wd) {
        if (!OSUtils.isWindows()) {
            try {
                java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                Point position = new Point(0, 0);
                wd.manage().window().setPosition(position);
                Dimension maximizedScreenSize =
                        new Dimension((int) screenSize.getWidth(), (int) screenSize.getHeight());
                wd.manage().window().setSize(maximizedScreenSize);
            } catch (HeadlessException e) {
                LOGGER.warn("GraphicsEnvironment.isHeadless(), can't maximize screen by getting actual resolution");
            }
        } else {
            wd.manage().window().maximize();
        }
    }

    public RemoteWebDriver getDriver(String clientName) throws MalformedURLException {
        BrowserClientType browserClientType = BrowserClientType.valueOf(clientName.toUpperCase());
        switch (browserClientType) {
            case GC:
                this.webDriver = startChrome();
                break;
            default:
                this.webDriver = startChrome();
                break;
        }
        maximizeWindow(this.webDriver);
        this.webDriver.manage().deleteAllCookies();
        return this.webDriver;
    }

    /**
     * Launches Chrome natively or on the remote machine according to settings
     * Old fashioned way, need to be refactored according to latest drivers and Selenium changes.
     */
    private RemoteWebDriver startChrome() throws MalformedURLException {
        OSUtils.killProcess("chromedriver.exe");
        if (System.getProperty("webdriver.chrome.driver") == null) {
            String chromeDriverName;
            if (OSUtils.isWindows()) {
                chromeDriverName = "chromedriver.exe";
            } else chromeDriverName = "chromedriver";
            String chromedriverPath = currentThread().getContextClassLoader().getResource(chromeDriverName).getPath();
            LOGGER.warn("webdriver.chrome.driver is not set. will now try to use [" + chromedriverPath + "]");
            System.setProperty("webdriver.chrome.driver", chromedriverPath);
        }
        return this.webDriver = new ChromeDriver();
    }

    public RemoteWebDriver getWebDriver() {
        return webDriver;
    }

    enum BrowserClientType {
        GC("gc");

        private String browserClientName;

        BrowserClientType(final String browserClientName) {
            this.browserClientName = browserClientName;
        }

        @Override
        public String toString() {
            return browserClientName;
        }
    }
}