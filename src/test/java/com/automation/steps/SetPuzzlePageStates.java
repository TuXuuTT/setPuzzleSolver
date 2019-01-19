package com.automation.steps;

import com.automation.BasicTest;
import com.automation.dto.SetCard;
import com.automation.utils.SetPuzzleSolvingUtil;
import org.openqa.selenium.WebElement;
import pageobjects.SetPuzzlePage;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SetPuzzlePageStates {

    private SetPuzzlePage homePage = new SetPuzzlePage(BasicTest.getWd());

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
        } else
            throw new IllegalArgumentException(String.format("Error parsing image number from %s", imageLink));
    }

    private String getClassNameFromCard(WebElement cardImage) {
        return cardImage.getAttribute("class");
    }
}
