package com.automation.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@EqualsAndHashCode
public class SetCard {
    private String className;
    private int shading;
    private int symbol;
    private int color;
    private int symbolsNumber;
}
