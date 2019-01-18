package com.automation.steps;

import com.automation.BasicTest;
import pageobjects.SetPuzzleHomePage;

import java.util.List;

import static com.automation.utils.PuzzleImageConverter.getAllValidSetsAsImageNumbers;

public class SetPuzzleHomePageActions {
    private SetPuzzleHomePage homePage = new SetPuzzleHomePage(BasicTest.getWd());

    public void openHomePage() {
        homePage.loadApp();
    }

    public void clickThroughAllValidSets(int[] imageNumbers) {
        List<int[]> allValidSets = getAllValidSetsAsImageNumbers(imageNumbers);
        for (int i = 0; i < allValidSets.size(); i++) {
            clickValidSet(allValidSets.get(i));
            if (i < allValidSets.size() - 1) {
                homePage.waitForTextToBePresentIn(homePage.getSetMessageLabelLocator(), "GREAT!");
            }
            homePage.waitForInvisibility(homePage.getSetMessageLabelLocator());
        }
    }

    public void clickValidSet(int[] setImageNumbers) {
        for (int number : setImageNumbers) {
            homePage.click(homePage.getAllCardsImagesList().stream().filter(img -> img.getAttribute("src").contains("/" + number + "."))
                    .findAny().orElseThrow(() -> new IllegalArgumentException("Erorr finidng image with nubmer " + number)));
        }
    }
}
