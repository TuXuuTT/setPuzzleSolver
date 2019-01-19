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
                .shading((int) Math.floor(((imageNumber % 81) / 27) + 1))
                .symbol((int) Math.floor(((imageNumber % 27) / 9) + 1))
                .color((int) Math.floor(((imageNumber % 9) / 3) + 1))
                .symbolsNumber((int) Math.floor(((imageNumber % 3)) + 1))
                .build();
    }

    private static int getImageNumberFromCard(SetCard card) {
        int imageNumber;
        imageNumber = (card.getShading() - 1) * 27;
        imageNumber += (card.getSymbol() - 1) * 9;
        imageNumber += (card.getColor() - 1) * 3;
        imageNumber += card.getSymbolsNumber();
        return imageNumber;
    }

    private static boolean isAttributeValid(int cardAttribute1, int cardAttribute2, int cardAttribute3) {
        return (((cardAttribute1 == cardAttribute2) && (cardAttribute1 == cardAttribute3)) || ((cardAttribute1 + cardAttribute2 + cardAttribute3) == 6));
    }

    private static boolean isSetValid(SetCard[] threeCardsSet) {
        boolean result = false;
        if (isAttributeValid(threeCardsSet[0].getSymbolsNumber(), threeCardsSet[1].getSymbolsNumber(), threeCardsSet[2].getSymbolsNumber())) {
            Logger.out.debug(String.format("Number in set %s is valid", Arrays.toString(threeCardsSet)));
            if (isAttributeValid(threeCardsSet[0].getSymbol(), threeCardsSet[1].getSymbol(), threeCardsSet[2].getSymbol())) {
                Logger.out.debug(String.format("Symbol in set %s is valid", Arrays.toString(threeCardsSet)));
                if (isAttributeValid(threeCardsSet[0].getShading(), threeCardsSet[1].getShading(), threeCardsSet[2].getShading())) {
                    Logger.out.debug(String.format("Shading in set %s is valid", Arrays.toString(threeCardsSet)));
                    if (isAttributeValid(threeCardsSet[0].getColor(), threeCardsSet[1].getColor(), threeCardsSet[2].getColor())) {
                        Logger.out.debug(String.format("Color in set %s is valid", Arrays.toString(threeCardsSet)));
                        Logger.out.info(String.format("Set %s is valid", Arrays.toString(threeCardsSet)));
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
//
//            Symbol.SWIGGLE = 1;
//            Symbol.DIAMOND = 2;
//            Symbol.OVAL    = 3;
//
//            Color.RED = 1;
//            Color.PURPLE = 2;
//            Color.GREEN = 3;
//
//            Shape.SOLID = 1;
//            Shape.LINED = 2;
//            Shape.EMPTY = 3;
