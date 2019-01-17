package pageobjects;

import com.automation.environment.EnvironmentConfigurator;
import com.automation.logger.Logger;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;


public abstract class BasicPage {
    public static final int WAIT_MEDIUM_SECONDS = 30;
    public static final int POLLING_INTERVAL_MILLIS = 750;
    private final WebDriver wd;
    private Wait<WebDriver> visibilityWait;
    private Wait<WebDriver> invisibilityWait;

    public BasicPage(WebDriver wd) {
        this.wd = wd;
        visibilityWait = new FluentWait<>(getWebDriverCurrent())
                .withTimeout(Duration.ofSeconds(WAIT_MEDIUM_SECONDS * 10L))
                .pollingEvery(Duration.ofMillis(POLLING_INTERVAL_MILLIS))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);

        invisibilityWait
                = new FluentWait<>(getWebDriverCurrent())
                .withTimeout(Duration.ofSeconds(WAIT_MEDIUM_SECONDS * 10L))
                .pollingEvery(Duration.ofMillis(POLLING_INTERVAL_MILLIS))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);

        PageFactory.initElements(new AjaxElementLocatorFactory(wd, WAIT_MEDIUM_SECONDS), this);
    }

    public static String getAppURL() {
        return EnvironmentConfigurator.getInstance().getAppUrl();
    }

    public WebDriver getWebDriverCurrent() {
        return wd;
    }

    public void setWebDriverWindowSize(int width, int height) {
        wd.manage().window().setPosition(new Point(0, 0));
        wd.manage().window().setSize(new Dimension(width, height));
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void loadApp() {
        loadUrl(getAppURL());
        waitForPageToLoad(WAIT_MEDIUM_SECONDS);
    }

    public void loadUrl(String url) {
        Logger.out.info("Loading page: {}", url);
        wd.get(url);
    }


    /**
     * Pause the test to wait for the page to display completely.
     * This is not normally recommended practice, but is useful from time to time.
     */
    public void waitABit(final long delayInMilliseconds) {
        try {
            Thread.sleep(delayInMilliseconds);
        } catch (InterruptedException e) {
            Logger.out.warn("Wait a bit method was interrupted.", e);
        }
    }

    public void waitForPageToLoad(final long milliseconds) {
        waitForPageToLoad(getWebDriverCurrent(), milliseconds);
    }

    private void waitForPageToLoad(final WebDriver driver, final long timeout) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofMillis(timeout))
                .pollingEvery(Duration.ofMillis(1000));
        final Boolean[] result = {false};
        wait.until(webDriver -> {
            result[0] = ("complete").equals(executeJS("return document.readyState").toString());
            return result[0];
        });
    }

    protected void sendKeys(final WebElement webElement, String text) {
        waitForClickable(webElement);
        webElement.clear();
        webElement.sendKeys(text);
    }

    protected WebElement waitForVisibility(WebElement webElement) {
        try {
            visibilityWait.until(ExpectedConditions.visibilityOf(webElement));
        } catch (NoSuchElementException nse) {
            Logger.out.info("Try to wait little more (wait for visibility)");
            nse.printStackTrace();
            throw nse;
        }
        return webElement;
    }

    protected void waitForInvisibility(final By locator) {
        try {
            invisibilityWait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (NoSuchElementException e) {
            Logger.out.info("Try to wait little more (wait for invisibility)");
            e.printStackTrace();
        }
    }

    protected WebElement waitForClickable(WebElement webElement) {
        waitForVisibility(webElement);
        try {
            visibilityWait.until(ExpectedConditions.elementToBeClickable(webElement));
        } catch (NoSuchElementException nse) {
            Logger.out.info("Try to wait little more (wait for clickable)");
            nse.printStackTrace();
        }
        return webElement;
    }

    protected boolean click(WebElement webElement) {
        boolean result = false;
        int attempts = 0;
        while (attempts < 3) {
            try {
                waitForClickable(webElement).click();
                result = true;
                break;
            } catch (StaleElementReferenceException e) {
                attempts++;
            }
        }
        return result;
    }

    protected boolean isElementPresent(final WebElement we) {
        try {
            return we.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected void moveMouseCursorToWebElement(WebElement webElement) {
//        scrollToElement(webElement);
        waitForClickable(webElement);
        Actions action = new Actions(getWebDriverCurrent());
        action.moveToElement(webElement).perform();
    }

    protected Object executeJS(final String script, final Object... params) {
        return ((JavascriptExecutor) getWebDriverCurrent()).executeScript(script, params);
    }

    protected WebElement scrollToElement(WebElement we) {
        executeJS("arguments[0].scrollIntoView(true);", we);
        return we;
    }


    @Step
    public void refreshPage() {
        getWebDriverCurrent().navigate().refresh();
    }

    protected void waitForElementStopMoving(WebElement element) {
        Point a;
        Point b;
        do {
            a = element.getLocation();
            try {
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            b = element.getLocation();
        } while (!a.equals(b));
    }
}
