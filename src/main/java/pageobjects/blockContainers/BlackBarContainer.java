package pageobjects.blockContainers;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BlackBarContainer {

    @FindBy(css = "a.btnChangeBackground")
    private WebElement changeBckgrndBtn;


    public void changeBackground() {
        changeBckgrndBtn.click();
    }

}
