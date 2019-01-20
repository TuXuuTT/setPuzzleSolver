package com.automation.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@EqualsAndHashCode
public class SetCard {
    private String className;
    private Shading shading;
    private Symbol symbol;
    private Color color;
    private int symbolsNumber;

    @Override
    public String toString() {
        return "Card: {" +
                "className='" + className + '\'' +
                ", " + shading +
                ", " + symbol +
                ", " + color +
                ", symbols=" + symbolsNumber +
                '}';
    }

    @Getter
    public static class Shading {
        private int code;
        private String name;

        public Shading(int code) {
            this.code = code;
            switch (code) {
                case 1:
                    name = "SOLID";
                    break;
                case 2:
                    name = "LINED";
                    break;
                case 3:
                    name = "EMPTY";
                    break;
            }
        }

        @Override
        public String toString() {
            return "Shading{" +
                    "#" + code +
                    ", '" + name + '\'' +
                    '}';
        }
    }

    @Getter
    public static class Symbol {
        private int code;
        private String name;

        public Symbol(int code) {
            this.code = code;
            switch (code) {
                case 1:
                    name = "SWIGGLE";
                    break;
                case 2:
                    name = "DIAMOND";
                    break;
                case 3:
                    name = "OVAL";
                    break;
            }
        }

        @Override
        public String toString() {
            return "Symbol{" +
                    "#" + code +
                    ", '" + name + '\'' +
                    '}';
        }
    }

    @Getter
    public static class Color {
        private int code;
        private String name;

        public Color(int code) {
            this.code = code;
            switch (code) {
                case 1:
                    name = "RED";
                    break;
                case 2:
                    name = "PURPLE";
                    break;
                case 3:
                    name = "GREEN";
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported attribute code: " + code);
            }
        }

        @Override
        public String toString() {
            return "Color{" +
                    "#" + code +
                    ", '" + name + '\'' +
                    '}';
        }
    }
}
