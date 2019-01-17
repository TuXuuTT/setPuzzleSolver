package com.automation.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SetCardDTO {
    private int shading;
    private int symbol;
    private int color;
    private int number;
}
