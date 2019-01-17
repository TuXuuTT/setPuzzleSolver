package com.automation.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SetCardDTO {

    private int form;

    private int color;

    private int shape;

}
