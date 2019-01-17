package com.automation.utils;

import com.automation.dto.SetCardDTO;
import com.automation.logger.Logger;

public abstract class PuzzleImageConverter {

    private static SetCardDTO[] cards;

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

    private boolean isFeatureValid(int f1, int f2, int f3) {
        return (((f1 == f2) && (f1 == f3)) || ((f1 + f2 + f3) == 6));
    }

    private boolean isValidSet() {
        int reason = VALID_SET;
        int base = 0;
        int product = 0;
        Logger.out.info(this);
        if (cards[0] == null || cards[1] == null || cards[2] == null) {
            return INVALID_CARDS;
        }
        if (isFeatureValid(cards[0].getNumber(), cards[1].getNumber(), cards[2].getNumber())) {
            Logger.out.info("number valid");
            if (isFeatureValid(cards[0].getSymbol(), cards[1].getSymbol(), cards[2].getSymbol())) {
                Logger.out.info("symbol valid");
                if (isFeatureValid(cards[0].getShading(), cards[1].getShading(), cards[2].getShading())) {
                    Logger.out.info("shading valid");
                    if (isFeatureValid(cards[0].getColor(), cards[1].getColor(), cards[2].getColor())) {
                        Logger.out.info("color valid");
                        // Valid Set
                        Logger.out.info("its valid");
                    } else {
                        base = COLOR;
                        product = cards[0].color * cards[1].color * cards[2].color;
                    }
                } else {
                    base = SHADING;
                    product = cards[0].shading * cards[1].shading * cards[2].shading;
                }
            } else {
                base = SYMBOL;
                product = cards[0].symbol * cards[1].symbol * cards[2].symbol;
            }
        }
    }
}
