package com.automation.testCucmbrRunners;


import com.automation.BasicTest;
import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;

@CucumberOptions(features = "src/test/resources/features/SetPuzzleSmokeTest.feature",
        glue = "com.automation.stepDefinitions",
        plugin = {"html:build/reports/cucumber-report", "pretty"},
        strict = true,
        snippets = SnippetType.CAMELCASE,
        tags = {/*"~@LOAD"}*/}) //TODO Tags are for dev purposes only. For CI use tags from command line)
public class TestSetPuzzleSmokeTestRunner extends BasicTest {
}
