package com.automation.utils;

import com.automation.dto.SetCard;
import com.automation.logger.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SetPuzzleSolvingUtil {

    private SetPuzzleSolvingUtil() {
    }

    public static SetCard getCardObject(String className, int imageNumber) {
        if ((imageNumber < 1) || (imageNumber > 81)) {
            throw new IllegalArgumentException("Tried to create card with imageNumber %s which is out of range");
        }
        imageNumber--;
        return SetCard.builder()
                .className(className)
                .shading(new SetCard.Shading((int) Math.floor(((imageNumber % 81) / 27) + 1)))
                .symbol(new SetCard.Symbol((int) Math.floor(((imageNumber % 27) / 9) + 1)))
                .color(new SetCard.Color((int) Math.floor(((imageNumber % 9) / 3) + 1)))
                .symbolsNumber((int) Math.floor(((imageNumber % 3)) + 1))
                .build();
    }

    private static int getImageNumberFromCard(SetCard card) {
        int imageNumber;
        imageNumber = (card.getShading().getCode() - 1) * 27;
        imageNumber += (card.getSymbol().getCode() - 1) * 9;
        imageNumber += (card.getColor().getCode() - 1) * 3;
        imageNumber += card.getSymbolsNumber();
        return imageNumber;
    }

    private static boolean isAttributeValid(int cardAttributeCode1, int cardAttributeCode2, int cardAttributeCode3) {
        return (((cardAttributeCode1 == cardAttributeCode2)
                && (cardAttributeCode1 == cardAttributeCode3))
                || ((cardAttributeCode1 + cardAttributeCode2 + cardAttributeCode3) == 6));
    }

    private static boolean isSetValid(SetCard[] threeCardsSet) {
        boolean result = false;
        if (isAttributeValid(threeCardsSet[0].getSymbolsNumber(), threeCardsSet[1].getSymbolsNumber(), threeCardsSet[2].getSymbolsNumber())) {
            Logger.out.debug(String.format("Number in set %s is valid", Arrays.toString(threeCardsSet)));
            if (isAttributeValid(threeCardsSet[0].getSymbol().getCode(), threeCardsSet[1].getSymbol().getCode(), threeCardsSet[2].getSymbol().getCode())) {
                Logger.out.debug(String.format("Symbol in set %s is valid", Arrays.toString(threeCardsSet)));
                if (isAttributeValid(threeCardsSet[0].getShading().getCode(), threeCardsSet[1].getShading().getCode(), threeCardsSet[2].getShading().getCode())) {
                    Logger.out.debug(String.format("Shading in set %s is valid", Arrays.toString(threeCardsSet)));
                    if (isAttributeValid(threeCardsSet[0].getColor().getCode(), threeCardsSet[1].getColor().getCode(), threeCardsSet[2].getColor().getCode())) {
                        Logger.out.debug(String.format("Color in set %s is valid", Arrays.toString(threeCardsSet)));
                        Logger.out.info(String.format("Set is valid: %s", Arrays.toString(threeCardsSet)));
                        result = true;
                    }
                }
            }
        }
        return result;
    }

    public static List<SetCard[]> findAllSets(SetCard[] fullSetOfCards) {
        List<SetCard[]> result = new ArrayList<>();
        for (int i = 0; i < fullSetOfCards.length - 2; i++) {
            for (int j = i + 1; j < fullSetOfCards.length - 1; j++) {
                for (int k = j + 1; k < fullSetOfCards.length; k++) {
                    SetCard[] threeCardsSet = new SetCard[3];
                    threeCardsSet[0] = fullSetOfCards[i];
                    threeCardsSet[1] = fullSetOfCards[j];
                    threeCardsSet[2] = fullSetOfCards[k];
                    if (isSetValid(threeCardsSet)) {
                        result.add(threeCardsSet);
                    }
                }
            }
        }
        return result;
    }
}
