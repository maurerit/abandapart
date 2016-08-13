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
import com.aba.industry.model.FreightDetails;
import com.ullink.slack.simpleslackapi.SlackPersona;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.text.DecimalFormat;
import java.text.Format;
import java.util.List;
import java.util.Map;

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
               .append( "* _(" )
               .append( result.getProductId() )
               .append( ")_\n" )
               .append( "*Build Request Details*\n" )
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

            //<editor-fold desc="Invention Overheads">
            message.append( "\n*_Overheads_*" )
                   .append( "\n    Installation: " )
                   .append( format.format( result.getInventionResult()
                                                 .getInstallationFees() ) )
                   .append( "\n    Installation Tax: " )
                   .append( format.format( result.getInventionResult()
                                                 .getInstallationTax() ) )
                   .append( "\n    Salary: " )
                   .append( format.format( result.getInventionResult()
                                                 .getSalaryCost() ) )
                   .append( "\n" );
            //</editor-fold>
        }

        //TODO: abandapart-20 Format required skills

        if ( !hasInvention ) {
            message.append( "\n*_Overheads_*" );
        }

        //<editor-fold desc="Build Overheads">
        message.append( "\nBuild\n    Installation: " )
               .append( format.format( result.getInstallationFees() ) )
               .append( "\n    Installation Tax: " )
               .append( format.format( result.getInstallationTax() ) )
               .append( "\n    Salary: " )
               .append( format.format( result.getSalaryCost() ) );
        //</editor-fold>

        //<editor-fold desc="Freight Overheads">
        boolean hasToFreight = false;
        if ( result.getToBuildLocationFreight() != null ) {
            hasToFreight = true;

            message.append( "\n_Freight_" )
                   .append( "\n  to " )
                   .append( result.getBuildSystem() );
            for ( Map.Entry<String, FreightDetails> entry : result.getToBuildLocationFreight()
                                                                  .entrySet() ) {
                message.append( "\n    - " )
                       .append( entry.getKey() )
                       .append( ": " )
                       .append( format.format( entry.getValue()
                                                    .getCharge() ) );
            }
        }
        if ( result.getFromBuildLocationFreight() != null ) {
            if ( !hasToFreight ) {
                message.append( "\n_Freight_" );
            }
            message.append( "\n  from " )
                   .append( result.getBuildSystem() );
            for ( Map.Entry<String, FreightDetails> entry : result.getFromBuildLocationFreight()
                                                                  .entrySet() ) {
                message.append( "\n    - " )
                       .append( entry.getKey() )
                       .append( ": " )
                       .append( format.format( entry.getValue()
                                                    .getCharge() ) );
            }
        }
        //</editor-fold>

        message.append( "\n" )
               .append( "*Total Cost: " )
               .append( format.format( result.getTotalCost() ) )
               .append( "*" );

        return message.toString();
    }

    private static void formatMaterialsWithCost ( List<ActivityMaterialWithCost> materials, Format format,
                                                  StringBuilder message )
    {
        message.append( "\n```" );
        for ( ActivityMaterialWithCost amWithCost : materials ) {
            message.append( "\nx" )
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