package com.automation.steps;

import com.automation.BasicTest;
import com.automation.dto.SetCard;
import com.automation.logger.Logger;
import com.automation.utils.SetPuzzleSolvingUtil;
import org.openqa.selenium.WebElement;
import pageobjects.pages.SetPuzzlePage;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SetPuzzlePageStates {

    private SetPuzzlePage homePage = new SetPuzzlePage(BasicTest.getWebDriver());

    public List<SetCard> getAllTodaysCards() {
        return homePage.getAllCardsImagesList().stream()
                .map(cardImage -> SetPuzzleSolvingUtil.getCardObject(getClassNameFromCard(cardImage), getImageNumberFromCard(cardImage)))
                .collect(Collectors.toList());
    }

    private int getImageNumberFromCard(WebElement cardImage) {
        Pattern pattern = Pattern.compile("[^/]*(?=\\.[^.]+($|\\?))");
        String imageLink = cardImage.getAttribute("src");
        Matcher m = pattern.matcher(imageLink);
        if (m.find()) {
            return Integer.parseInt(m.group(0));
        } else {
            String errorMessage = String.format("Error parsing image number from %s", imageLink);
            Logger.out.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
    }

    private String getClassNameFromCard(WebElement cardImage) {
        return cardImage.getAttribute("class");
    }
}
