/*
 * Copyright 2016 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.aba.industry.bot.service.impl;

import com.aba.TypeIdNotFoundException;
import com.aba.data.domain.config.BuildOrBuyConfiguration;
import com.aba.data.domain.config.IndustrySkillConfiguration;
import com.aba.data.domain.config.InventionSkillConfiguration;
import com.aba.industry.bot.commands.PreferencesCommands;
import com.aba.industry.bot.model.Preferences;
import com.aba.industry.bot.repo.PreferencesRepository;
import com.aba.industry.bot.responder.impl.TypeIdNotFoundResponder;
import com.aba.industry.bot.service.PreferencesService;
import com.aba.industry.fetch.client.TypeNameToTypeIdProvider;
import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.events.SlackMessagePosted;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by maurerit on 8/23/16.
 */
@Service
@ConfigurationProperties( prefix = "aba.industry.preferences", exceptionIfInvalid = false )
public class PreferencesServiceImpl implements PreferencesService {
    @Autowired
    private SlackSession session;

    @Autowired
    private PreferencesRepository preferencesRepository;

    @Autowired
    private TypeIdNotFoundResponder typeIdNotFoundResponder;

    @Autowired
    private TypeNameToTypeIdProvider typeIdProvider;

    @Value( "${aba.industry.preferences.industry.industrySkillLevel:5}" )
    private Integer industrySkillLevel;

    @Value( "${aba.industry.preferences.industry.advancedIndustrySkillLevel:5}" )
    private Integer advancedIndustrySkillLevel;

    @Value( "${aba.industry.preferences.invention.encryptionSkillLevel:4}" )
    private Integer encryptionSkillLevel;

    @Value( "${aba.industry.preferences.invention.datacoreSkillOneLevel:3}" )
    private Integer datacoreSkillOneLevel;

    @Value( "${aba.industry.preferences.invention.datacoreSkillTwoLevel:3}" )
    private Integer datacoreSkillTwoLevel;

    @Value( "${aba.industry.preferences.industry.suppressSalary:false}" )
    private Boolean suppressSalary;

    @Value( "${aba.industry.preferences.industry.suppressFreight:false}" )
    private Boolean suppressFreight;

    @Value( "${aba.industry.preferences.industry.suppressInstallation:false}" )
    private Boolean suppressInstallation;

    @Override
    public void processCommand ( PreferencesCommands command, SlackMessagePosted event )
    {
        switch ( command ) {
            case BUILT_VS_BOUGHT:
                handleBuildVsBoughtCommand( command, event );
                break;
            case SKILLS:
                handleSkillsCommand( command, event );
                break;
            case OVERHEAD:
                handleOverheadCommand( command, event );
                break;
        }
    }

    @Override
    public Preferences getPreferencesForUser ( String userId ) {
        Preferences result = null;

        Preferences userPrefs = preferencesRepository.findUniqueByUserId( userId );
        Preferences globalPrefs = preferencesRepository.findUniqueByUserId( "global" );

        //region Build missing global configurations
        if ( globalPrefs == null ) {
            globalPrefs = new Preferences();
            globalPrefs.setIndustrySkillConfiguration( new IndustrySkillConfiguration() );
            globalPrefs.setInventionSkillConfiguration( new InventionSkillConfiguration() );

            globalPrefs.getIndustrySkillConfiguration()
                       .setIndustrySkillLevel( industrySkillLevel );
            globalPrefs.getIndustrySkillConfiguration()
                       .setAdvancedIndustrySkillLevel( advancedIndustrySkillLevel );

            globalPrefs.getInventionSkillConfiguration()
                       .setEncryptionSkillLevel( encryptionSkillLevel );
            globalPrefs.getInventionSkillConfiguration()
                       .setDatacoreOneSkillLevel( datacoreSkillOneLevel );
            globalPrefs.getInventionSkillConfiguration()
                       .setDatacoreTwoSkillLevel( datacoreSkillTwoLevel );

            globalPrefs.setSuppressSalary( suppressSalary );
            globalPrefs.setSuppressFreight( suppressFreight );
            globalPrefs.setSuppressInstallation( suppressInstallation );

            globalPrefs.setUserId( "global" );

            preferencesRepository.save( globalPrefs );
        }
        //endregion

        if ( userPrefs == null ) {
            result = globalPrefs;
        }
        else {
            result = userPrefs;

            //region Merge User and Global Skill preferences
            if ( userPrefs.getIndustrySkillConfiguration() == null ) {
                userPrefs.setIndustrySkillConfiguration( globalPrefs.getIndustrySkillConfiguration() );
            }

            if ( userPrefs.getIndustrySkillConfiguration() != null && userPrefs.getIndustrySkillConfiguration()
                                                                               .getIndustrySkillLevel() == null )
            {
                userPrefs.getIndustrySkillConfiguration()
                         .setIndustrySkillLevel( globalPrefs.getIndustrySkillConfiguration()
                                                            .getIndustrySkillLevel() );
            }

            if ( userPrefs.getIndustrySkillConfiguration() != null && userPrefs.getIndustrySkillConfiguration()
                                                                               .getAdvancedIndustrySkillLevel() ==
                    null )
            {
                userPrefs.getIndustrySkillConfiguration()
                         .setAdvancedIndustrySkillLevel( globalPrefs.getIndustrySkillConfiguration()
                                                                    .getAdvancedIndustrySkillLevel() );
            }

            if ( userPrefs.getInventionSkillConfiguration() == null ) {
                userPrefs.setInventionSkillConfiguration( globalPrefs.getInventionSkillConfiguration() );
            }

            if ( userPrefs.getInventionSkillConfiguration() != null && userPrefs.getInventionSkillConfiguration()
                                                                                .getDatacoreOneSkillLevel() == null )
            {
                userPrefs.getInventionSkillConfiguration()
                         .setDatacoreOneSkillLevel( globalPrefs.getInventionSkillConfiguration()
                                                               .getDatacoreOneSkillLevel() );
            }

            if ( userPrefs.getInventionSkillConfiguration() != null && userPrefs.getInventionSkillConfiguration()
                                                                                .getDatacoreTwoSkillLevel() == null )
            {
                userPrefs.getInventionSkillConfiguration()
                         .setDatacoreTwoSkillLevel( globalPrefs.getInventionSkillConfiguration()
                                                               .getDatacoreTwoSkillLevel() );
            }

            if ( userPrefs.getInventionSkillConfiguration() != null && userPrefs.getInventionSkillConfiguration()
                                                                                .getEncryptionSkillLevel() == null )
            {
                userPrefs.getInventionSkillConfiguration()
                         .setEncryptionSkillLevel( globalPrefs.getInventionSkillConfiguration()
                                                              .getEncryptionSkillLevel() );
            }
            //endregion

            //region Merge Suppression Preferences
            if ( userPrefs.getSuppressSalary() == null ) {
                userPrefs.setSuppressSalary( globalPrefs.getSuppressSalary() );
            }

            if ( userPrefs.getSuppressFreight() == null ) {
                userPrefs.setSuppressFreight( globalPrefs.getSuppressFreight() );
            }

            if ( userPrefs.getSuppressInstallation() == null ) {
                userPrefs.setSuppressInstallation( globalPrefs.getSuppressInstallation() );
            }
            //endregion
        }

        return result;
    }

    private void handleBuildVsBoughtCommand ( PreferencesCommands command, SlackMessagePosted event )
    {
        Preferences prefs = preferencesRepository.findUniqueByUserId( event.getSender()
                                                                           .getId() );

        if ( prefs == null ) {
            prefs = new Preferences();
            prefs.setUserId( event.getSender()
                                  .getId() );
        }

        if ( prefs.getBuildOrBuyConfigurations() == null ) {
            prefs.setBuildOrBuyConfigurations( new HashMap<>() );
        }

        String[] segments = command.getInterestingSegments( event.getMessageContent() );


        Integer typeId = null;
        try {
            typeId = typeIdProvider.getTypeIdForTypeName( segments[0] );
        }
        catch ( TypeIdNotFoundException e ) {
            typeIdNotFoundResponder.reportError( session, event, e.getMessage(), e.getTypeName() );
        }
        catch ( IOException e ) {
            throw new RuntimeException( e );
        }

        prefs.getBuildOrBuyConfigurations()
             .put( segments[0], new BuildOrBuyConfiguration( typeId,
                                                             BuildOrBuyConfiguration.BuildOrBuy.valueOf(
                                                                     segments[1].toUpperCase() ) ) );

        preferencesRepository.save( prefs );
    }

    private void handleSkillsCommand ( PreferencesCommands command, SlackMessagePosted event )
    {
        Preferences prefs = preferencesRepository.findUniqueByUserId( event.getSender()
                                                                           .getId() );

        if ( prefs == null ) {
            prefs = new Preferences();
            prefs.setUserId( event.getSender()
                                  .getId() );
        }

        if ( prefs.getIndustrySkillConfiguration() == null ) {
            prefs.setIndustrySkillConfiguration( new IndustrySkillConfiguration() );
        }

        if ( prefs.getInventionSkillConfiguration() == null ) {
            prefs.setInventionSkillConfiguration( new InventionSkillConfiguration() );
        }

        String[] segments = command.getInterestingSegments( event.getMessageContent() );

        Integer value = Integer.parseInt( segments[1] );

        switch ( segments[0] ) {
            case PreferencesCommands.DATACORE_SKILL_ONE_NAME:
                prefs.getInventionSkillConfiguration()
                     .setDatacoreOneSkillLevel( value );
                break;
            case PreferencesCommands.DATACORE_SKILL_TWO_NAME:
                prefs.getInventionSkillConfiguration()
                     .setDatacoreTwoSkillLevel( value );
                break;
            case PreferencesCommands.ENCRYPTION_SKILL_NAME:
                prefs.getInventionSkillConfiguration()
                     .setEncryptionSkillLevel( value );
                break;
            case PreferencesCommands.INDUSTRY_SKILL_NAME:
                prefs.getIndustrySkillConfiguration()
                     .setIndustrySkillLevel( value );
                break;
            case PreferencesCommands.ADVANCED_INDUSTRY_SKILL:
                prefs.getIndustrySkillConfiguration()
                     .setAdvancedIndustrySkillLevel( value );
                break;
        }

        preferencesRepository.save( prefs );
    }

    private void handleOverheadCommand ( PreferencesCommands command, SlackMessagePosted event )
    {
        Preferences prefs = preferencesRepository.findUniqueByUserId( event.getSender()
                                                                           .getId() );

        if ( prefs == null ) {
            prefs = new Preferences();
            prefs.setUserId( event.getSender()
                                  .getId() );
        }

        String[] segments = command.getInterestingSegments( event.getMessageContent() );

        Boolean value = "on".equalsIgnoreCase( segments[1] );
        //Invert the value, the users will say ov Freight off (thinking they're turning freight off) but we're acting
        //as if this is a suppression command from them.
        value = !value;

        switch ( segments[0] ) {
            case PreferencesCommands.SALARY_NAME:
                prefs.setSuppressSalary( value );
                break;
            case PreferencesCommands.FREIGHT_NAME:
                prefs.setSuppressFreight( value );
                break;
            case PreferencesCommands.INSTALLATION_NAME:
                prefs.setSuppressInstallation( value );
                break;
        }

        preferencesRepository.save( prefs );
    }
}
