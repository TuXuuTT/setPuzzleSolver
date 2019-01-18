package pageobjects;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Getter
public class SetPuzzleHomePage extends BasicPage {

    private By setMessageLabelLocator = By.cssSelector("div.set-messages");
//    @FindBy(css = "div.set-card-td")
//    private List<WebElement> allCardsList;

    @FindBy(css = "div.set-card-td img")
    private List<WebElement> allCardsImagesList;

    public SetPuzzleHomePage(WebDriver wd) {
        super(wd);
    }
}
