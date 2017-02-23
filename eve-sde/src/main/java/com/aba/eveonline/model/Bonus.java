package com.aba.eveonline.model;

import lombok.Data;

/**
 * Created by mm66053 on 2/10/2017.
 */
@Data
public class Bonus {
    private Integer nameID;
    private Double bonus;
    private I18NString bonusText;
    private Integer importance;
    private Integer unitID;
}
