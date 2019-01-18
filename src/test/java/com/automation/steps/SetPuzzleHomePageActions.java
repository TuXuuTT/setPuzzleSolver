package com.automation.steps;

import com.automation.BasicTest;
import pageobjects.SetPuzzleHomePage;

import static com.automation.utils.PuzzleImageConverter.findAllValidSetsOfImageNumbers;

public class SetPuzzleHomePageActions {
    private SetPuzzleHomePage homePage = new SetPuzzleHomePage(BasicTest.getWd());

    public void openHomePage() {
        homePage.loadApp();
    }

    public void clickThroughAllValidSets(int[] imageNumbers) {
        findAllValidSetsOfImageNumbers(imageNumbers).forEach(this::clickValidSet);
    }

    public void clickValidSet(int[] setImageNumbers) {
        for (int number : setImageNumbers) {
            homePage.getAllCardsImagesList().stream().filter(img -> img.getAttribute("src").contains("/" + number + "."))
                    .findAny().orElseThrow(() -> new IllegalArgumentException("Erorr finidng image with nubmer " + number))
                    .click();
        }
        homePage.waitForTextToBePresentIn(homePage.getSetMessageLabelLocator(), "GREAT!");
        homePage.waitForInvisibility(homePage.getSetMessageLabelLocator());
    }
}
