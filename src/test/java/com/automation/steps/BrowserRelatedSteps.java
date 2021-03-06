package com.automation.steps;

import com.automation.BasicTest;
import pageobjects.pages.CommonPage;

public class BrowserRelatedSteps {

    private CommonPage commonPage = new CommonPage(BasicTest.getWebDriver());

    public boolean isCurrentPageTitleContains(String text) {
        commonPage.waitForTitleToContain(text);
        return commonPage.getWebDriverCurrent().getTitle().contains(text);
    }

    public void openUrl(String url) {
        commonPage.loadUrl(url);
    }
}
