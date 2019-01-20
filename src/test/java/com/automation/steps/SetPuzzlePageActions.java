package com.automation.steps;

import com.automation.BasicTest;
import com.automation.dto.SetCard;
import com.automation.logger.Logger;
import pageobjects.SetPuzzlePage;

import java.util.Arrays;
import java.util.List;

import static com.automation.utils.SetPuzzleSolvingUtil.findAllSets;

public class SetPuzzlePageActions {
    private SetPuzzlePage homePage = new SetPuzzlePage(BasicTest.getWd());

    public void openHomePage() {
        homePage.loadApp();
    }

    public void clickThroughAllValidSets(List<SetCard> allTodaysCards) {
        List<SetCard[]> allValidSets = findAllSets(allTodaysCards.toArray(new SetCard[0]));
        for (int i = 0; i < allValidSets.size(); i++) {
            clickValidSet(allValidSets.get(i));
            if (i < allValidSets.size() - 1) {
                homePage.waitForTextToBePresentIn(homePage.getSetMessageLabelLocator(), "GREAT!");
            }
            Logger.out.info("Set is completed");
            homePage.waitForInvisibility(homePage.getSetMessageLabelLocator());
        }
    }

    public void clickValidSet(SetCard[] setCards) {
        Arrays.stream(setCards).forEach(card -> {
            homePage.click(homePage.getCardByClassname(card.getClassName()));
            Logger.out.info(String.format("Clicking card %s", card.getClassName()));
        });
    }
}
