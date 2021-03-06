/*
 * Copyright 2017 maurerit
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
import com.aba.industry.data.service.WarehouseService;
import com.aba.industry.fetch.client.BuildRequirementsProvider;
import com.aba.industry.fetch.client.CostIndexProvider;
import com.aba.industry.invention.InventionCalculator;
import com.aba.industry.manufacturing.ManufacturingCalculator;
import com.aba.industry.model.*;
import com.aba.industry.model.fuzzysteve.BlueprintData;
import com.aba.industry.model.fuzzysteve.SystemCostIndexes;
import com.aba.industry.overhead.OverheadCalculator;
import com.aba.market.fetch.MarketPriceSearcher;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static com.aba.market.TradeHubs.AMARR;
import static com.aba.market.TradeHubs.JITA;

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
    private WarehouseService warehouseService;

    @Autowired
    private MarketPriceSearcher marketPriceSearcher;

    @Autowired
    private SolarSystemRepository solarSystemRepository;

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private ItemTypeRepository itemTypeRepository;

    @Override
    @Deprecated
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
    @Deprecated
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
        request.setEntityId(0l);
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
        catch ( NoSuchElementException e ) {
            logger.warn( "No Such Blueprint {}", request.getRequestedBuildTypeId() );
            return null;
        }

        long jitaId = solarSystemRepository.getSolarSystemId( JITA.getSystemName() );
        long theForgeId = regionRepository.findRegionId( JITA.getRegionName() );

        List<BuildCalculationResult> childResults = putCostsIntoBlueprintData( theForgeId, jitaId, blueprintData,
                                                                               request );

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
        if ( itemTypeRepository.fetchItemDetails( request.getRequestedBuildTypeId() ) != null ) {
            result.setProductName( itemTypeRepository.fetchItemDetails( request.getRequestedBuildTypeId() )
                                                     .getName() );
        }
        else {
            logger.warn( "Could not find typeId {}", request.getRequestedBuildTypeId() );
        }

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
        result.setChildBuilds( childResults );

        return result;
    }

    //TODO: Refactor this into a cost provider or something?
    private List<BuildCalculationResult> putCostsIntoBlueprintData ( long regionId, long systemId,
                                                                     BlueprintData blueprintData,
                                                                     BuildCalculationRequest request ) throws
            IOException
    {
        List<BuildCalculationResult> results = new ArrayList<>();

        if ( blueprintData.getActivityMaterials()
                          .get( IndustryActivities.INVENTION.getActivityId() ) != null )
        {
            for ( ActivityMaterialWithCost am : blueprintData.getActivityMaterials()
                                                             .get( IndustryActivities.INVENTION.getActivityId() ) ) {
                putCostsIntoMaterialCost( regionId, systemId, am, request );
            }
        }

        for ( ActivityMaterialWithCost am : blueprintData.getActivityMaterials()
                                                         .get( IndustryActivities.MANUFACTURING.getActivityId() ) ) {
            results.add( putCostsIntoMaterialCost( regionId, systemId, am, request ) );
        }

        if ( blueprintData.getBlueprintDetails()
                          .getPrecursorTypeId() != null )
        {
            int precursorTypeId = blueprintData.getBlueprintDetails()
                                               .getPrecursorTypeId();
            blueprintData.getBlueprintDetails()
                         .setPrecursorAdjustedPrice( marketPriceSearcher.getAdjustedPrice( precursorTypeId ) );
        }

        return results;
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
        logger.debug( "Recieved: {} as item to build.", result.getProductName() );

        //region Freight Calculations
        if ( !suppressFreight ) {
            long jitaId = solarSystemRepository.getSolarSystemId( JITA.getSystemName() );
            long amarrId = solarSystemRepository.getSolarSystemId( AMARR.getSystemName() );

            Double jitaLowestSell = marketPriceSearcher.getLowestSellPrice(
                    regionRepository.findRegionId( "The Forge" ),
                    jitaId,
                    result.getProductId() );
            logger.debug( "Jita Lowest Sell: {}", jitaLowestSell );

            Double amarrLowestSell = marketPriceSearcher.getLowestSellPrice( regionRepository.findRegionId( "Domain" ),
                                                                             amarrId,
                                                                             result.getProductId() );
            logger.debug( "Amarr Lowest Sell: {}", amarrLowestSell );
            //TODO: Maybe I need to start dealing in BigDecimals instead of rounding error prone floating point values?
            FreightDetails toJitaFreight = overheadCalculator.getFreightDetails( JITA.getSystemName(), systemName,
                                                                                 (double) Math.round(
                                                                                         result.getMaterialCost()
                                                                                 ) );
            result.getToBuildLocationFreight()
                  .put( JITA.getSystemName(), toJitaFreight );
            logger.debug( "To Jita Freight: {}", toJitaFreight );

            FreightDetails toAmarrFreight = overheadCalculator.getFreightDetails( AMARR.getSystemName(), systemName,
                                                                                  (double) Math.round(
                                                                                          result.getMaterialCost()
                                                                                  ) );
            result.getToBuildLocationFreight()
                  .put( AMARR.getSystemName(), toAmarrFreight );
            logger.debug( "To Amarry Freight: {}", toAmarrFreight );


            //TODO: Situations where there is no item listed make a configurable desirable profit ratio
            if ( jitaLowestSell == null || jitaLowestSell < 1 ) {
                FreightDetails fromJitaFreight = overheadCalculator.getFreightDetails( systemName, JITA.getSystemName(),
                                                                                       (double) Math.round(
                                                                                               result.getMaterialCost()
                                                                                       ) );
                result.getFromBuildLocationFreight()
                      .put( JITA.getSystemName(), fromJitaFreight );
                logger.debug( "From Jita Freight (material costs): {}", fromJitaFreight );
            }
            else {
                FreightDetails fromJitaFreight = overheadCalculator.getFreightDetails( systemName, JITA.getSystemName(),
                                                                                       jitaLowestSell );
                result.getFromBuildLocationFreight()
                      .put( JITA.getSystemName(), fromJitaFreight );
                logger.debug( "From Jita Freight (lowest price): {}", fromJitaFreight );
            }

            if ( amarrLowestSell == null || amarrLowestSell < 1 ) {
                FreightDetails fromAmarrFreight = overheadCalculator.getFreightDetails( systemName,
                                                                                        AMARR.getSystemName(),
                                                                                        (double) Math.round(
                                                                                                result.getMaterialCost()
                                                                                        ) );
                result.getFromBuildLocationFreight()
                      .put( AMARR.getSystemName(), fromAmarrFreight );
                logger.debug( "From Amarr Freight (material costs): {}", fromAmarrFreight );
            }
            else {
                FreightDetails fromAmarrFreight = overheadCalculator.getFreightDetails( systemName,
                                                                                        AMARR.getSystemName(),
                                                                                        amarrLowestSell );
                result.getFromBuildLocationFreight()
                      .put( AMARR.getSystemName(), fromAmarrFreight );
                logger.debug( "From Amarr Freight (lowest price): {}", fromAmarrFreight );
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

    private BuildCalculationResult putCostsIntoMaterialCost ( long regionId, long systemId, ActivityMaterialWithCost am,
                                                              BuildCalculationRequest request ) throws IOException
    {
        BuildCalculationResult result = null;

        Integer itemId = am.getTypeId();
        long quantity = am.getQuantity();

        if ( request != null ) {
            BuildOrBuyConfiguration configuration = buildOrBuyService.findByTypeId( itemId );

            if ( configuration != null && configuration.getBuildOrBuy() == BuildOrBuyConfiguration.BuildOrBuy.BUILD ) {
                BuildCalculationRequest newRequest = request.copy();
                newRequest.setRequestedBuildTypeId( itemId );
                newRequest.setRequestedBuildTypeName( am.getName() );

                result = this.calculateBuild( newRequest );
            }
        }

        Double cost = null;
        CostSource source = null;

        if ( result != null ) {
            cost = result.getTotalCost();
            source = CostSource.BUILT;
        }
        else {
            WarehouseResponse warehouseResponse = warehouseService.getPriceForQuantity( request.getEntityId(), regionId, systemId, itemId, quantity );
            if ( warehouseResponse != null ) {
                cost = warehouseResponse.calcPricePerEach();
                source = CostSource.WAREHOUSE;
            }
            else{
                cost = 0d;
                source = CostSource.NO_PRICE;
            }
        }

        Double adjustedCost = marketPriceSearcher.getAdjustedPrice( itemId );

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
        am.setName( itemTypeRepository.fetchItemDetails( am.getTypeId() )
                                      .getName() );

        return result;
    }
}
