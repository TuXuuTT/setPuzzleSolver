package pageobjects.pages;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.BasicPage;

import java.util.List;

public class SetPuzzlePage extends BasicPage {

    @Getter
    private By setMessageLabelLocator = By.cssSelector("div.set-messages");
    private String cardImageCssLocator = "img.%s";

    @Getter
    @FindBy(css = "div.set-card-td img")
    private List<WebElement> allCardsImagesList;

    @Getter
    @FindBy(css = "center.set-game-wrapper")
    private WebElement cardsTableWrapper;

    public SetPuzzlePage(WebDriver webDriver) {
        super(webDriver);
    }

    public WebElement getCardByClassname(String className) {
        return getWebDriverCurrent().findElement(By.cssSelector(String.format(cardImageCssLocator, className)));
    }
}
