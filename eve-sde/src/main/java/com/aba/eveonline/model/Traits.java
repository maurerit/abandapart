package com.aba.eveonline.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Created by mm66053 on 2/10/2017.
 */
@Data
public class Traits {
    private List<Bonus> miscBonuses;
    private List<Bonus> roleBonuses;
    private Map<Integer, List<Bonus>> types;
}
