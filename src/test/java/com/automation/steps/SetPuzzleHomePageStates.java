package com.automation.steps;

import com.automation.BasicTest;
import pageobjects.SetPuzzleHomePage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SetPuzzleHomePageStates {

    private SetPuzzleHomePage homePage = new SetPuzzleHomePage(BasicTest.getWd());

    public int[] getTodaysCardsImagesNumbers() {
        Pattern pattern = Pattern.compile("[^/]*(?=\\.[^.]+($|\\?))");
        return homePage.getAllCardsImagesList().stream()
                .map(card -> card.getAttribute("src"))
                .mapToInt(link -> {
                    Matcher m = pattern.matcher(link);
                    if (m.find()) {
                        return Integer.parseInt(m.group(0));
                    } else
                        throw new IllegalArgumentException(String.format("Error parsing image number from %s", link));
                }).toArray();
    }
}
