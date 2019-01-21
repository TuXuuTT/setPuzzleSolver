package com.automation.browserClient;

import com.automation.environment.EnvironmentConfigurator;
import com.automation.logger.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;

import static java.lang.Thread.currentThread;

public class BrowserClient {

    protected static EnvironmentConfigurator environmentConfigurator;
    private RemoteWebDriver webDriver;

    public BrowserClient() {
        environmentConfigurator = EnvironmentConfigurator.getInstance();
    }

    public static void maximizeWindow(WebDriver webDriver) {
        if (!OSUtils.isWindows()) {
            try {
                java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                Point position = new Point(0, 0);
                webDriver.manage().window().setPosition(position);
                Dimension maximizedScreenSize =
                        new Dimension((int) screenSize.getWidth(), (int) screenSize.getHeight());
                webDriver.manage().window().setSize(maximizedScreenSize);
            } catch (HeadlessException e) {
                Logger.out.warn("GraphicsEnvironment.isHeadless(), can't maximize screen by getting actual resolution");
            }
        } else {
            webDriver.manage().window().maximize();
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
     * Launches Chrome. Forcibly make chromedriver for mac executable, to avoid IllegalStateException: The driver is not executable
     */
    private RemoteWebDriver startChrome() throws MalformedURLException {
        OSUtils.killProcess("chromedriver.exe");
        if (System.getProperty("webdriver.chrome.driver") == null) {
            String chromeDriverName = "chromedriver.exe";
            String chromedriverPath = currentThread().getContextClassLoader().getResource(chromeDriverName).getPath();
            if (!OSUtils.isWindows()) {
                chromeDriverName = "chromedriver";
                chromedriverPath = currentThread().getContextClassLoader().getResource(chromeDriverName).getPath();
                try {
                    OSUtils.runCommand("chmod u+x " + chromedriverPath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            Logger.out.warn("webdriver.chrome.driver is not set. will now try to use [" + chromedriverPath + "]");
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