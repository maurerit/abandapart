package com.aba.industry.model;

import lombok.Data;

/**
 * Static enumeration to hold all available industrial activities.
 * 
 * @author maurerit
 */
@Data
public enum Activities {
    INVENTION("Invention", 8),
    MANUFACTURING("Manufacturing", 1)
    ;

    private final String activityName;
    private final Integer activityId;

    Activities ( String activityName, Integer activityId ) {
        this.activityName = activityName;
        this.activityId = activityId;
    }
}
