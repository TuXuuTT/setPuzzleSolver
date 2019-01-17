package com.automation.steps;

import com.automation.BasicTest;
import pageobjects.SetPuzzleHomePage;

public class BrowserRelatedActions {

    private SetPuzzleHomePage homePage = new SetPuzzleHomePage(BasicTest.getWd());

    public void openApp(){
        homePage.loadApp();
    }
}
