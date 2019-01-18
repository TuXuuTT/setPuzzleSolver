package com.automation.stepDefinitions;

import com.automation.steps.SetPuzzleHomePageActions;
import com.automation.steps.SetPuzzleHomePageStates;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class SetPuzzleHomePageDefs {

    SetPuzzleHomePageActions setPuzzleHomePageActions = new SetPuzzleHomePageActions();
    SetPuzzleHomePageStates setPuzzleHomePageStates = new SetPuzzleHomePageStates();

    @Given("^user has opened Set Puzzle home page$")
    public void userHasOpenedSetPuzzleHomePage() {
        setPuzzleHomePageActions.openHomePage();
    }

    @When("^user finds and clicks all correct sets$")
    public void userFindsAndClicksAllCorrectSets() {
        int[] imgNumbers = setPuzzleHomePageStates.getTodaysCardsImagesNumbers();
        setPuzzleHomePageActions.clickThroughAllValidSets(imgNumbers);
    }
}
