/*
 * Copyright 2016 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.aba.industry.bot.util;

import com.aba.industry.model.ActivityMaterialWithCost;
import com.aba.industry.model.BuildCalculationResult;
import com.ullink.slack.simpleslackapi.SlackPersona;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.text.DecimalFormat;
import java.text.Format;
import java.util.List;

/**
 * Created by maurerit on 8/9/16.
 */
public class MessageUtils {
    public static String formatUserForClicky ( SlackPersona slackUser ) {
        return "<@" + slackUser.getId() + "|" + slackUser.getUserName() + ">";
    }

    public static String formatStackTrace ( Throwable e ) {
        StringBuilder result = new StringBuilder();

        result.append( ExceptionUtils.getStackTrace( e ) );

        return result.toString();
    }

    public static String formatBuildCalculationResult ( BuildCalculationResult result ) {
        Format format = new DecimalFormat();
        boolean hasInvention = false;

        StringBuilder message = new StringBuilder();

        message.append( "*" )
               .append( result.getProductName() )
               .append( " Build Request Details*\n" )
               .append( "Materials\n    for the build" );

        if ( result.getMaterialsWithCost() != null ) {
            formatMaterialsWithCost( result.getMaterialsWithCost(), format, message );
        }

        if ( result.getInventionResult() != null && result.getInventionResult()
                                                          .getMaterialsWithCost() != null )
        {
            hasInvention = true;

            message.append( "    for invention\n" );
            formatMaterialsWithCost( result.getInventionResult()
                                           .getMaterialsWithCost(), format, message );

            message.append( "\n*Overheads*" );
        }

        //TODO: abandapart-20 Format required skills

        if ( !hasInvention ) {
            message.append( "\nOverheads" );
        }

        message.append( "Build Installation: " )
               .append( result.getInstallationFees() )
               .append( "\nBuild Installation Tax: " )
               .append( result.getInstallationTax() )
               .append( "\nBuild Salary: " )
               .append( result.getSalaryCost() );

        return message.toString();
    }

    private static void formatMaterialsWithCost ( List<ActivityMaterialWithCost> materials, Format format,
                                                  StringBuilder message )
    {
        for ( ActivityMaterialWithCost amWithCost : materials ) {
            message.append( "\n``` " )
                   .append( " x" )
                   .append( amWithCost.getQuantity() )
                   .append( " " )
                   .append( amWithCost.getName() )
                   .append( " @ " )
                   .append( format.format( amWithCost.getCost() ) )
                   .append( " using " )
                   .append( amWithCost.getSource()
                                      .name() );
        }

        message.append( "```\n" );
    }

    public static String formatSecondsToDDHHMMSS ( Long buildSeconds ) {
        StringBuilder result = new StringBuilder();

        Integer days = new Double( Math.floor( buildSeconds.doubleValue() / 86400 ) ).intValue();
        Integer hours = new Double( Math.floor( ( buildSeconds.doubleValue() - ( days * 86400 ) ) / 3600 ) ).intValue();
        Integer minutes = new Double(
                Math.floor(
                        ( buildSeconds.doubleValue() - ( ( days * 86400 ) + ( hours * 3600 ) ) ) / 60 ) ).intValue();
        Integer seconds = new Double(
                ( buildSeconds.doubleValue() - ( ( days * 86400 ) + hours * 3600 ) ) - ( minutes * 60 ) ).intValue();

        if ( days > 1 ) {
            result.append( days )
                  .append( " Days " );
        }
        else if ( days > 0 ) {
            result.append( days )
                  .append( " Day " );
        }

        if ( hours < 10 ) {
            result.append( "0" );
        }

        result.append( hours )
              .append( ":" );

        if ( minutes < 10 ) {
            result.append( "0" );
        }

        result.append( minutes )
              .append( ":" );

        if ( seconds < 10 ) {
            result.append( "0" );
        }

        result.append( seconds );

        return result.toString();
    }
}
