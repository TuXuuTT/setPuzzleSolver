package com.automation.steps;

import com.automation.BasicTest;
import pageobjects.CommonPage;

public class BrowserRelatedSteps {

    private CommonPage commonPage = new CommonPage(BasicTest.getWd());

    public String getCurrentPageTitle() {
        return commonPage.getWebDriverCurrent().getTitle();
    }

    public void openUrl(String url) {
        commonPage.loadUrl(url);
    }
}
