package com.automation.stepDefinitions;

import com.automation.steps.BrowserRelatedActions;
import cucumber.api.java.en.Given;

public class SetPuzzleHomePageDefs {

    BrowserRelatedActions browserRelatedActions = new BrowserRelatedActions();

    @Given("^user has opened Set Puzzle home page$")
    public void userHasOpenedSetPuzzleHomePage() {
        browserRelatedActions.openApp();
    }
}
