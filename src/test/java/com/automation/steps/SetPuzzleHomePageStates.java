package com.automation.steps;

import com.automation.BasicTest;
import pageobjects.SetPuzzleHomePage;

public class SetPuzzleHomePageStates {

    private SetPuzzleHomePage homePage = new SetPuzzleHomePage(BasicTest.getWd());

    public String getHomePageTitle() {
        return homePage.getWebDriverCurrent().getTitle();
    }
}
