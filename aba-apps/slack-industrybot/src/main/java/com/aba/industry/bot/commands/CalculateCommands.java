/*
 * Copyright 2016 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.aba.industry.bot.commands;

import lombok.Getter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by maurerit on 8/7/16.
 */
@Getter
public enum CalculateCommands {
    /**
     * States to calculate the build cost choosing all defaults.
     * <pre>
     * T1:
     *     ME: 10
     *     TE: 20
     *     Industry Skill: 5
     *     AI Skill: 5
     * T2:
     *     ME: 2
     *     TE: 4
     *     Industry Skill: 5
     *     AI Skill: 5
     *     Decryptor One: 3
     *     Decryptor Two: 3
     *     Encryptions Methods: 4
     *
     * Will not dive down the build chain and assume everything is bought at sell prices
     * </pre>
     */
    CALCULATE_BUILD_BASIC( "calculate", ".*calculate +([a-zA-Z0-9]+[a-zA-Z0-9 -]+)",
                           "To calculate build costs for a Sleipnir using default settings ask me:\n" +
                                   "> calculate *Sleipnir*!\n", 1 ),
    CALCULATE_BUILD_SPECIFY_PROCURE_TYPE( "calculate",
                                          ".*calculate ([a-zA-Z0-9]+[a-zA-Z0-9 -]+) (using|with) (buy|sell).*",
                                          "To calculate build costs of a 150mm Railgun II using buy prices ask me:\n" +
                                                  "> calculate *150mm Railgun II* with *buy* prices :D?\n" +
                                                  "or\n" +
                                                  "> calculate *150mm Railgun II* using *buy* prices :P!\n", 1, 3 );

    private String    triggerWord;
    private String    patternStr;
    private Integer[] groupsOfInterest;
    private Pattern   pattern;
    private String    helpText;

    CalculateCommands ( String triggerWord, String patternStr, String helpText, Integer... groupsOfInterest ) {
        this.triggerWord = triggerWord;
        this.patternStr = patternStr;
        this.pattern = Pattern.compile( patternStr );
        this.helpText = helpText;
        this.groupsOfInterest = groupsOfInterest;
    }

    public static CalculateCommands findCommand ( String message ) {
        CalculateCommands command = null;

        for ( CalculateCommands currentCommand : values() ) {
            Matcher matcher = currentCommand.pattern.matcher( message );
            if ( matcher.find() ) {
                command = currentCommand;

                String[] segments = command.getInterestingSegments( message );

                if ( segments.length == 0 ) {
                    command = null;
                }
            }
        }

        return command;
    }

    public String[] getInterestingSegments ( String message ) {
        String[] interestingSegments = new String[groupsOfInterest.length];

        Matcher matcher = pattern.matcher( message );

        if ( matcher.find() ) {
            for ( int idx = 0; idx < groupsOfInterest.length; idx++ ) {
                interestingSegments[idx] = matcher.group( groupsOfInterest[idx] );
            }
        }

        return interestingSegments;
    }
}
