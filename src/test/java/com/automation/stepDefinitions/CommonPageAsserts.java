package com.automation.stepDefinitions;

import com.automation.steps.BrowserRelatedSteps;
import cucumber.api.java.en.Then;

import static org.assertj.core.api.Assertions.assertThat;

public class CommonPageAsserts {

    private BrowserRelatedSteps commonSteps = new BrowserRelatedSteps();

    @Then("^current page title has text (.*)$")
    public void checkUrlContains(String text) {
        assertThat(commonSteps.getCurrentPageTitle()).as("Home page title does not contain expected text").contains(text);
    }
}
