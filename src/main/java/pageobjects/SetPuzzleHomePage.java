package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.blockContainers.BlackBarContainer;

public class SetPuzzleHomePage extends BasicPage {

    @FindBy(css = "div.black_bar")
    private BlackBarContainer blackBarContainer;

    @FindBy(css = "div#SetPuzzleOuterContainer.won")
    private WebElement redWinBackgroundLabel;


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
