package com.automation.stepDefinitions;

import com.automation.steps.SetPuzzleHomePageStates;
import cucumber.api.java.en.Then;

import static org.assertj.core.api.Assertions.assertThat;

public class SetPuzzleHomePageAsserts {

    SetPuzzleHomePageStates homePageStates = new SetPuzzleHomePageStates();

    @Then("^current page url has text (.*)$")
    public void checkUrlContains(String text) {
        assertThat(homePageStates.getHomePageTitle()).as("Home page title does not contain expected text").contains(text);
    }
}
