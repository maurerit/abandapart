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
 * Created by maurerit on 8/22/16.
 */
@Getter
public enum PreferencesCommands {
    BUILT_VS_BOUGHT( "bvb", "bvb +([a-zA-Z0-9]+[a-zA-Z0-9 -]+) (build|buy)",
                     "To tell me to build Particle Accelerator Unit and not buy:\n" +
                             "> bvb *Particle Accelerator Unit* *build*!\n", 1, 2 ),
    SKILLS( "skill", "skill +([a-zA-Z0-9]+[a-zA-Z0-9 -]+) ([1-5])",
            "Skills are handled as generically as possible.\n" +
                    "This really only affects invention because I take all the\n" +
                    "datacore skills and call them either _Datacore Skill One_\n" +
                    "or _Datacore Skill Two_ and call all the encryption\n" +
                    "skills _Encryption Skill_.  This eases configuration for you if\n" +
                    "you are uniform in your approach to skilling but more complicated\n" +
                    "if you aren't quite up in all of your training :(.\n" +
                    "To configure the Datacore Skill One skill to five:\n" +
                    "> skill *Datacore Skill One* *5*!\n", 1, 2 ),
    OVERHEAD( "ov", "ov +([fF]reight|[sS]alary|[iI]nstallation) (show|noshow)",
              "To turn frieght calculations off tell me:\n" +
                      "> ov *Freight* *off*!\n" +
                      "I also accept Salary, and Installation.", 1, 2 );

    public static final String DATACORE_SKILL_ONE_NAME = "Datacore Skill One";
    public static final String DATACORE_SKILL_TWO_NAME = "Datacore Skill Two";
    public static final String ENCRYPTION_SKILL_NAME = "Encyption Skill";

    public static final String INDUSTRY_SKILL_NAME = "Industry";
    public static final String ADVANCED_INDUSTRY_SKILL = "Advanced Industry";

    private String    triggerWord;
    private String    patternStr;
    private Integer[] groupsOfInterest;
    private Pattern   pattern;
    private String    helpText;

    PreferencesCommands ( String triggerWord, String patternStr, String helpText, Integer... groupsOfInterest ) {
        this.triggerWord = triggerWord;
        this.patternStr = patternStr;
        this.pattern = Pattern.compile( patternStr );
        this.helpText = helpText;
        this.groupsOfInterest = groupsOfInterest;
    }

    public static PreferencesCommands findCommand ( String message ) {
        PreferencesCommands command = null;

        for ( PreferencesCommands currentCommand : values() ) {
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
