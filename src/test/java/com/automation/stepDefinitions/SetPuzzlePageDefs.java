package com.automation.stepDefinitions;

import com.automation.dto.SetCard;
import com.automation.steps.SetPuzzlePageActions;
import com.automation.steps.SetPuzzlePageStates;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

import java.util.List;

public class SetPuzzlePageDefs {

    SetPuzzlePageActions setPuzzlePageActions = new SetPuzzlePageActions();
    SetPuzzlePageStates setPuzzlePageStates = new SetPuzzlePageStates();

    @Given("^user has opened Set Puzzle home page$")
    public void userHasOpenedSetPuzzleHomePage() {
        setPuzzlePageActions.openHomePage();
    }

    @When("^user finds and clicks all correct sets$")
    public void userFindsAndClicksAllCorrectSets() {
        List<SetCard> todaysCards = setPuzzlePageStates.getAllTodaysCards();
        setPuzzlePageActions.clickThroughAllValidSets(todaysCards);
    }
}
