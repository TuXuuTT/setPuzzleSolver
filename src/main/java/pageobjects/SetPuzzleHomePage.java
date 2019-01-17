package pageobjects;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Getter
public class SetPuzzleHomePage extends BasicPage {

    @FindBy(css = "div.set-messages")
    private WebElement setMessageLabel;

    @FindBy(css = "div.set-card-td")
    private List<WebElement> allCardsList;

    private By tryMeButtonLocator = By.cssSelector("#tryMe");

    public SetPuzzleHomePage(WebDriver wd) {
        super(wd);
    }

//    public void verifySlotMachineDisplayed() {
//        blackBarContainer.verifySlotMachineDisplayed();
//    }
//
//    public int getCurrentBet() {
//        return overallSlotMachinesContainer.getCurrentlyDisplayedBet();
//    }
//
//    public void clickSpin() {
//        overallSlotMachinesContainer.spin();
//    }


}
