/*
 * Copyright 2016 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.aba.industry.service;

import com.aba.ApplicationException;
import com.aba.data.domain.config.*;
import com.aba.eveonline.repo.ItemTypeRepository;
import com.aba.eveonline.repo.RegionRepository;
import com.aba.eveonline.repo.SolarSystemRepository;
import com.aba.industry.config.BuildOrBuyConfigurationService;
import com.aba.industry.config.OverheadConfigurationService;
import com.aba.industry.fetch.client.BuildRequirementsProvider;
import com.aba.industry.fetch.client.CostIndexProvider;
import com.aba.industry.invention.InventionCalculator;
import com.aba.industry.manufacturing.ManufacturingCalculator;
import com.aba.industry.model.*;
import com.aba.industry.model.fuzzysteve.BlueprintData;
import com.aba.industry.model.fuzzysteve.SystemCostIndexes;
import com.aba.industry.overhead.OverheadCalculator;
import com.aba.market.fetch.MarketOrderFetcher;
import com.aba.market.fetch.MarketPriceFetcher;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.aba.industry.TradeHubs.AMARR;
import static com.aba.industry.TradeHubs.JITA;

@Service
@Setter
//TODO: Find a go way to bring the desired potential shopping/drop off locations into this method.
public class LocalIndustryCalculationService implements IndustryCalculationService {
    private static final Logger logger = LoggerFactory.getLogger( IndustryCalculationService.class );

    @Autowired
    private BuildOrBuyConfigurationService buildOrBuyService;

    @Autowired
    private BuildRequirementsProvider buildReqProvider;

    @Autowired
    private CostIndexProvider costIndexProvider;

    @Autowired
    private InventionCalculator inventionCalc;

    @Autowired
    private ManufacturingCalculator manufacturingCalc;

    @Autowired
    private OverheadConfigurationService overheadService;

    @Autowired
    private OverheadCalculator overheadCalculator;

    @Autowired
    private MarketOrderFetcher marketOrderFetcher;

    @Autowired
    private MarketPriceFetcher marketPriceFetcher;

    @Autowired
    private SolarSystemRepository solarSystemRepository;

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private ItemTypeRepository itemTypeRepository;

    @Override
    public BuildCalculationResult calculateBuildCosts ( String systemName, Integer outputTypeId )
    {
        IndustrySkillConfiguration industrySkills = new IndustrySkillConfiguration();
        industrySkills.setAdvancedIndustrySkillLevel( 5 );
        industrySkills.setAdvancedIndustrySkillLevelMultiplier( 0.0 );
        industrySkills.setIndustrySkillLevel( 5 );
        industrySkills.setIndustrySkillLevelMultiplier( 0.0 );
        industrySkills.setPreference( ConfigurationType.PREFERED );

        InventionSkillConfiguration inventionSkills = new InventionSkillConfiguration();
        inventionSkills.setDatacoreOneSkillLevel( 3 );
        inventionSkills.setDatacoreOneSkillLevelMultiplier( 0.0 );
        inventionSkills.setDatacoreTwoSkillLevel( 3 );
        inventionSkills.setDatacoreTwoSkillLevelMultiplier( 0.0 );
        inventionSkills.setEncryptionSkillLevel( 4 );
        inventionSkills.setEncryptionSkillLevelMultiplier( 0.0 );
        inventionSkills.setPreference( ConfigurationType.PREFERED );
        return this.calculateBuildCosts( systemName, outputTypeId, industrySkills, inventionSkills, 10, 20, null, true,
                                         false );
    }

    @Override
    //TODO: me and te should be determined by already existing BP's or from the output of invention process with a
    // decryptor
    //however, version one of this will be for speculation and will assume defaults
    //TODO: Refactor these inputs to be contained inside a BuildCalculationConfiguration or some such
    public BuildCalculationResult calculateBuildCosts (
            String systemName,
            Integer outputTypeId,
            IndustrySkillConfiguration industrySkills,
            InventionSkillConfiguration inventionSkills,
            Integer meLevel,
            Integer teLevel,
            Decryptor decryptor,
            boolean findCurrentPrices,
            boolean useBuildOrBuyConfigurations )
    {
        BuildCalculationRequest request = new BuildCalculationRequest();
        request.setSystemName( systemName );
        request.setRequestedBuildTypeId( outputTypeId );
        request.setIndustrySkills( industrySkills );
        request.setInventionSkills( inventionSkills );
        request.setMeLevel( meLevel );
        request.setTeLevel( teLevel );
        request.setDecryptor( decryptor );
        request.setFindPrices( findCurrentPrices );
        request.setUseBuildOrBuyConfigurations( useBuildOrBuyConfigurations );


        BuildCalculationResult result = null;
        try {
            result = calculateBuild( request );
        }
        catch ( IOException e ) {
            throw new RuntimeException( e );
        }

        return result;
    }

    @Override
    public BuildCalculationResult calculateBuild ( BuildCalculationRequest request ) throws IOException {
        BuildCalculationResult result;
        //TODO: Tax Rate should be determined by builders standing with station holder
        Double taxMultiplier = 1.0d;

        Integer meLevelToUse = request.getMeLevel();
        Integer teLevelToUse = request.getTeLevel();

        BlueprintData blueprintData = null;
        SystemCostIndexes costIndexes;
        try {
            blueprintData = buildReqProvider.getBlueprintData( request.getRequestedBuildTypeId() );
            costIndexes = costIndexProvider.getSystemCostIndexes( request.getSystemName() );
        }
        catch ( IOException e ) {
            logger.error( "Error while retrieving build requirements or cost indexes", e );
            throw new ApplicationException( e );
        }

        long jitaId = solarSystemRepository.getSolarSystemId( JITA.getSystemName() );
        long theForgeId = regionRepository.findRegionId( "The Forge" );

        putCostsIntoBlueprintData( theForgeId, jitaId, blueprintData );

        InventionCalculationResult inventionCalculationResult =
                getInventionCalculationResult( request.getInventionSkills(), request.getDecryptor(), taxMultiplier,
                                               blueprintData, costIndexes, request.getSuppressInstallation() );

        if ( inventionCalculationResult != null ) {
            meLevelToUse = inventionCalculationResult.getResultingME();
            teLevelToUse = inventionCalculationResult.getResultingTE();
        }

        result = this.manufacturingCalc.calculateBuildCost( costIndexes, taxMultiplier,
                                                            blueprintData,
                                                            meLevelToUse, teLevelToUse,
                                                            request.getIndustrySkills(),
                                                            request.getSuppressInstallation() );
        result.setProductName( itemTypeRepository.getItemDetails( request.getRequestedBuildTypeId() )
                                                 .getName() );

        if ( inventionCalculationResult != null ) {
            long baseInventionTime = blueprintData.getBlueprintDetails()
                                                  .getTimesInSeconds()
                                                  .get( IndustryActivities.INVENTION.getActivityId() );
            long inventionTime = Math.round(
                    ( (double) baseInventionTime ) * ( 1 - 0.03 * request.getIndustrySkills()
                                                                         .getAdvancedIndustrySkillLevel() ) );

            inventionCalculationResult.setSeconds( inventionTime );
        }

        result.setInventionResult( inventionCalculationResult );

        calculateOverheads( request.getSystemName(), result, request.getSuppressSalary(),
                            request.getSuppressFreight() );

        result.setBuildSystem( request.getSystemName() );

        return result;
    }

    //TODO: Refactor this into a cost provider or something?
    private void putCostsIntoBlueprintData ( long regionId, long systemId, BlueprintData blueprintData )
    {
        if ( blueprintData.getActivityMaterials()
                          .get( IndustryActivities.INVENTION.getActivityId() ) != null )
        {
            for ( ActivityMaterialWithCost am : blueprintData.getActivityMaterials()
                                                             .get( IndustryActivities.INVENTION.getActivityId() ) ) {
                putCostsIntoMaterialCost( regionId, systemId, am );
            }
        }

        for ( ActivityMaterialWithCost am : blueprintData.getActivityMaterials()
                                                         .get( IndustryActivities.MANUFACTURING.getActivityId() ) ) {
            putCostsIntoMaterialCost( regionId, systemId, am );
        }

        if ( blueprintData.getBlueprintDetails()
                          .getPrecursorTypeId() != null )
        {
            int precursorTypeId = blueprintData.getBlueprintDetails()
                                               .getPrecursorTypeId();
            blueprintData.getBlueprintDetails()
                         .setPrecursorAdjustedPrice( marketPriceFetcher.getAdjustedPrice( precursorTypeId ) );
        }
    }

    private InventionCalculationResult getInventionCalculationResult (
            InventionSkillConfiguration inventionSkills,
            Decryptor decryptor,
            Double taxRate,
            BlueprintData blueprintData,
            SystemCostIndexes costIndexes,
            Boolean suppressInstallation )
    {
        InventionCalculationResult inventionCalculationResult = null;
        if ( !blueprintData.getBlueprintDetails()
                           .getTechLevel()
                           .equals( 1 ) )
        {
            inventionCalculationResult = this.inventionCalc.calculateInventionCosts( costIndexes, taxRate,
                                                                                     blueprintData, decryptor,
                                                                                     inventionSkills,
                                                                                     suppressInstallation );
        }
        return inventionCalculationResult;
    }

    private void calculateOverheads ( String systemName, BuildCalculationResult result, Boolean suppressSalary,
                                      Boolean suppressFreight )
    {
        SalaryConfiguration salaryConfiguration = this.overheadService.getSalaryConfiguration();
        FreightConfiguration freightConfiguration = this.overheadService.getFreightConfiguration();

        //region Freight Calculations
        if ( !suppressFreight ) {
            long jitaId = solarSystemRepository.getSolarSystemId( JITA.getSystemName() );
            long amarrId = solarSystemRepository.getSolarSystemId( AMARR.getSystemName() );

            Double jitaLowestSell = marketOrderFetcher.getLowestSellPrice( regionRepository.findRegionId( "The Forge" ),
                                                                           jitaId,
                                                                           result.getProductId() );

            Double amarrLowestSell = marketOrderFetcher.getLowestSellPrice( regionRepository.findRegionId( "Domain" ),
                                                                            amarrId,
                                                                            result.getProductId() );
            //TODO: Maybe I need to start dealing in BigDecimals instead of rounding error prone floating point values?
            result.getToBuildLocationFreight()
                  .put( JITA.getSystemName(), overheadCalculator.getFreightDetails( JITA.getSystemName(), systemName,
                                                                                    (double) Math.round(
                                                                                            result.getMaterialCost()
                                                                                    ) ) );
            result.getToBuildLocationFreight()
                  .put( AMARR.getSystemName(), overheadCalculator.getFreightDetails( AMARR.getSystemName(), systemName,
                                                                                     (double) Math.round(
                                                                                             result.getMaterialCost()
                                                                                     ) ) );


            //TODO: Situations where there is no item listed make a configurable desirable profit ratio
            if ( jitaLowestSell == null || jitaLowestSell < 1 ) {
                result.getFromBuildLocationFreight()
                      .put( JITA.getSystemName(),
                            overheadCalculator.getFreightDetails( systemName, JITA.getSystemName(),
                                                                  (double) Math.round(
                                                                          result.getMaterialCost()
                                                                  ) ) );
            }
            else {
                result.getFromBuildLocationFreight()
                      .put( JITA.getSystemName(),
                            overheadCalculator.getFreightDetails( systemName, JITA.getSystemName(), jitaLowestSell ) );
            }

            if ( amarrLowestSell == null || amarrLowestSell < 1 ) {
                result.getFromBuildLocationFreight()
                      .put( AMARR.getSystemName(),
                            overheadCalculator.getFreightDetails( systemName, AMARR.getSystemName(),
                                                                  (double) Math.round(
                                                                          result.getMaterialCost()
                                                                  ) ) );
            }
            else {
                result.getFromBuildLocationFreight()
                      .put( AMARR.getSystemName(),
                            overheadCalculator.getFreightDetails( systemName, AMARR.getSystemName(),
                                                                  amarrLowestSell ) );
            }
        }
        //endregion

        //region Salary Calculations
        if ( !suppressSalary ) {
            result.setSalaryCost(
                    overheadCalculator.getSalary( IndustryActivities.MANUFACTURING, result.getSeconds() ) );

            if ( result.getInventionResult() != null ) {
                result.getInventionResult()
                      .setSalaryCost( overheadCalculator.getSalary(
                              IndustryActivities.INVENTION, result.getInventionResult()
                                                                  .getSeconds() ) );
            }
        }
        //endregion
    }

    private void putCostsIntoMaterialCost ( long regionId, long systemId, ActivityMaterialWithCost am )
    {
        long itemId = am.getTypeId();
        long quantity = am.getQuantity();

        Double cost = marketOrderFetcher.getPriceForQuantity( regionId, systemId, itemId, quantity );
        Double adjustedCost = marketPriceFetcher.getAdjustedPrice( itemId );
        CostSource source = CostSource.LIVE_MARKET_SELL;

        if ( adjustedCost == null || adjustedCost.equals( Double.NaN ) || adjustedCost.equals(
                Double.NEGATIVE_INFINITY ) ||
                adjustedCost.equals( Double.POSITIVE_INFINITY ) )
        {
            adjustedCost = 0.0;
            source = CostSource.NO_PRICE;
        }

        if ( cost == null || cost.equals( Double.NaN ) || cost.equals( Double.NEGATIVE_INFINITY ) || cost.equals( Double
                                                                                                                          .POSITIVE_INFINITY ) )
        {
            cost = adjustedCost;

            if ( source == CostSource.LIVE_MARKET_SELL ) {
                source = CostSource.LIVE_MARKET_ADJUSTED;
            }
        }

        am.setCost( cost );
        am.setAdjustedCost( adjustedCost );
        am.setSource( source );
        am.setName( itemTypeRepository.getItemDetails( am.getTypeId() )
                                      .getName() );
    }
}
