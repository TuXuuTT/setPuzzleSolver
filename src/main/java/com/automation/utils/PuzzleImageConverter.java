package com.automation.utils;

import com.automation.dto.SetCardDTO;
import com.automation.logger.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PuzzleImageConverter {

    private PuzzleImageConverter() {
    }

    public static SetCardDTO getCardFromImageNumber(int imageNumber) {
        if ((imageNumber < 1) || (imageNumber > 81)) {
            throw new IllegalArgumentException("Tried to create card with imageNumber %s which is out of range");
        }
        imageNumber--;
        return SetCardDTO.builder()
                .shading((int) Math.floor(((imageNumber % 81) / 27) + 1))
                .symbol((int) Math.floor(((imageNumber % 27) / 9) + 1))
                .color((int) Math.floor(((imageNumber % 9) / 3) + 1))
                .number((int) Math.floor(((imageNumber % 3)) + 1))
                .build();
    }

    private static int getImageNumberFromCard(SetCardDTO card) {
        int imageNumber;
        imageNumber = (card.getShading() - 1) * 27;
        imageNumber += (card.getSymbol() - 1) * 9;
        imageNumber += (card.getColor() - 1) * 3;
        imageNumber += card.getNumber();
        return imageNumber;
    }

    private static boolean isAttributeValid(int cardAttribute1, int cardAttribute2, int cardAttribute3) {
        return (((cardAttribute1 == cardAttribute2) && (cardAttribute1 == cardAttribute3)) || ((cardAttribute1 + cardAttribute2 + cardAttribute3) == 6));
    }

    private static boolean isSetValid(SetCardDTO[] threeCardsSet) {
        boolean result = false;
        if (isAttributeValid(threeCardsSet[0].getNumber(), threeCardsSet[1].getNumber(), threeCardsSet[2].getNumber())) {
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

    private static List<SetCardDTO[]> findAllSets(SetCardDTO[] fullSetOfCards) {
        List<SetCardDTO[]> result = new ArrayList<>();
        for (int i = 0; i < fullSetOfCards.length - 2; i++) {
            for (int j = i + 1; j < fullSetOfCards.length - 1; j++) {
                for (int k = j + 1; k < fullSetOfCards.length; k++) {
                    SetCardDTO[] threeCardsSet = new SetCardDTO[3];
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

    public static List<int[]> getAllValidSetsAsImageNumbers(int[] imageNumbers) {
        List<SetCardDTO> fullListOfCards = new ArrayList<>();
        Arrays.stream(imageNumbers).forEach(number -> fullListOfCards.add(getCardFromImageNumber(number)));

        return findAllSets(fullListOfCards.toArray(new SetCardDTO[0])).stream()
                .map(setCardDTOS -> Arrays.stream(setCardDTOS)
                        .mapToInt(PuzzleImageConverter::getImageNumberFromCard).toArray())
                .peek(set -> Logger.out.info(Arrays.toString(set)))
                .collect(Collectors.toList());
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
