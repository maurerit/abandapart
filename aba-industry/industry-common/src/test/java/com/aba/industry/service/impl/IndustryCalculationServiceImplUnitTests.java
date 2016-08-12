/*
 * Copyright 2016 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.aba.industry.service.impl;

import com.aba.data.domain.config.ConfigurationType;
import com.aba.data.domain.config.IndustrySkillConfiguration;
import com.aba.data.domain.config.InventionSkillConfiguration;
import com.aba.eveonline.repo.ItemTypeRepository;
import com.aba.eveonline.repo.RegionRepository;
import com.aba.eveonline.repo.SolarSystemRepository;
import com.aba.industry.ItemCost;
import com.aba.industry.config.OverheadConfigurationService;
import com.aba.industry.fetch.client.BuildRequirementsProvider;
import com.aba.industry.fetch.client.CostIndexProvider;
import com.aba.industry.invention.InventionCalculator;
import com.aba.industry.manufacturing.ManufacturingCalculator;
import com.aba.industry.model.*;
import com.aba.industry.model.fuzzysteve.BlueprintData;
import com.aba.industry.model.fuzzysteve.SystemCostIndexes;
import com.aba.industry.overhead.OverheadCalculator;
import com.aba.industry.service.IndustryCalculationService;
import com.aba.market.fetch.MarketOrderFetcher;
import com.aba.market.fetch.MarketPriceFetcher;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.devfleet.crest.model.CrestType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static com.aba.industry.HubSystemNames.AMARR;
import static com.aba.industry.HubSystemNames.JITA;

@RunWith( MockitoJUnitRunner.class )
public class IndustryCalculationServiceImplUnitTests {
    @Mock
    BuildRequirementsProvider    buildRequirementsProvider;
    @Mock
    CostIndexProvider            costIndexProvider;
    @Mock
    OverheadConfigurationService overheadConfigurationService;
    @Mock
    OverheadCalculator           overheadCalculator;
    @Mock
    InventionCalculator          inventionCalculator;
    @Mock
    ManufacturingCalculator      manufacturingCalculator;
    @Mock
    RegionRepository             regionRepository;
    @Mock
    MarketOrderFetcher           marketOrderFetcher;
    @Mock
    MarketPriceFetcher           marketPriceFetcher;
    @Mock
    private SolarSystemRepository solarSystemRepository;
    @Mock
    private ItemTypeRepository    itemTypeRepository;
    private ObjectMapper mapper = new ObjectMapper();
    private MapType                     mapType;
    private Map<Integer, ItemCost>      itemCosts;
    private SystemCostIndexes           costIndexes;
    private BlueprintData               bpData;
    private IndustrySkillConfiguration  industrySkills;
    private InventionSkillConfiguration inventionSkills;
    private InventionCalculationResult  inventionCalculationResult;
    private BuildCalculationResult      buildCalculationResult;
    private FreightDetails              jitaFreightDetails;
    private FreightDetails              amarrFreightDetails;
    @InjectMocks
    private IndustryCalculationService service = new IndustryCalculationServiceImpl();

    @Before
    public void dataSetupBeforeTests ( ) throws IOException
    {
        InputStream bpDetailsIS = IndustryCalculationServiceImplUnitTests.class.getResourceAsStream(
                "/testSleipnirWithNullDecryptor-BlueprintDetails.json" );
        InputStream costIndexesIS = IndustryCalculationServiceImplUnitTests.class.getResourceAsStream(
                "/testSleipnirWithNullDecryptor-CostIndexes.json" );
        InputStream itemCostIS = IndustryCalculationServiceImplUnitTests.class.getResourceAsStream(
                "/testSleipnirWithNullDecryptor-ItemCosts.json" );
        InputStream buildCalculationResultIS = IndustryCalculationServiceImplUnitTests.class.getResourceAsStream(
                "/testSleipnirWithNullDecryptor-Output.json" );

        TypeFactory typeFactory = mapper.getTypeFactory();
        mapType = typeFactory.constructMapType( HashMap.class, Integer.class, ItemCost.class );

        itemCosts = mapper.readValue( itemCostIS, mapType );

        costIndexes = mapper.readValue( costIndexesIS, SystemCostIndexes.class );
        bpData = mapper.readValue( bpDetailsIS, BlueprintData.class );
        buildCalculationResult = mapper.readValue( buildCalculationResultIS, BuildCalculationResult.class );

        inventionSkills = new InventionSkillConfiguration();

        inventionSkills.setDatacoreOneSkillLevel( 3 );
        inventionSkills.setDatacoreTwoSkillLevel( 3 );
        inventionSkills.setEncryptionSkillLevel( 4 );

        industrySkills = new IndustrySkillConfiguration();

        industrySkills.setAdvancedIndustrySkillLevel( 5 );
        industrySkills.setIndustrySkillLevel( 5 );
        industrySkills.setPreference( ConfigurationType.EXCEPTIONAL );

        for ( ActivityMaterialWithCost am : bpData.getActivityMaterials()
                                                  .get( IndustryActivities.INVENTION.getActivityId() ) ) {
            ItemCost ic = itemCosts.get( am.getTypeId() );

            am.setCost( ic.getSell() );
            am.setAdjustedCost( ic.getAdjusted() );
        }

        for ( ActivityMaterialWithCost am : bpData.getActivityMaterials()
                                                  .get( IndustryActivities.MANUFACTURING.getActivityId() ) ) {
            ItemCost ic = itemCosts.get( am.getTypeId() );

            am.setCost( ic.getSell() );
            am.setAdjustedCost( ic.getAdjusted() );
        }

        inventionCalculationResult = new InventionCalculationResult();
        inventionCalculationResult.setSeconds( 2000L );
        inventionCalculationResult.setResultingME( 2 );
        inventionCalculationResult.setResultingTE( 4 );
        inventionCalculationResult.setResultingRuns( 1L );

        jitaFreightDetails = new FreightDetails();

        jitaFreightDetails.setCharge( 13000000d );
        jitaFreightDetails.setJumps( 15 );

        amarrFreightDetails = new FreightDetails();

        amarrFreightDetails.setCharge( 6000000d );
        amarrFreightDetails.setJumps( 9 );
    }

    @Test
    public void testThatServiceCombinesCalculationResults ( ) throws IOException
    {
        Mockito.when( buildRequirementsProvider.getBlueprintData( 22444 ) )
               .thenReturn( this.bpData );
        Mockito.when( costIndexProvider.getSystemCostIndexes( "Atreen" ) )
               .thenReturn( this.costIndexes );
        //<editor-fold desc="Invention and Manufacturing result mocks">
        Mockito.when( inventionCalculator.calculateInventionCosts( costIndexes, 1.0d, bpData, null,
                                                                   inventionSkills ) )
               .thenReturn( this.inventionCalculationResult );
        Mockito.when( manufacturingCalculator.calculateBuildCost( costIndexes, 1.0d, bpData, 2, 4, industrySkills ) )
               .thenReturn( buildCalculationResult );
        //</editor-fold>
        //<editor-fold desc="Overhead mocks">
        Mockito.when( overheadConfigurationService.getSalaryConfiguration() )
               .thenReturn( null );
        Mockito.when( overheadConfigurationService.getFreightConfiguration() )
               .thenReturn( null );

        Mockito.when( overheadCalculator.getSalary( IndustryActivities.INVENTION, 2000L ) )
               .thenReturn( ( inventionCalculationResult.getSalaryCost() / 60 / 60 / 40 ) * 200000 );
        Mockito.when(
                overheadCalculator.getSalary( IndustryActivities.MANUFACTURING, buildCalculationResult.getSeconds() ) )
               .thenReturn( buildCalculationResult.getSeconds()
                                                  .doubleValue() / 60 / 60 / 2 * 200000 );
        Mockito.when(
                overheadCalculator.getFreightDetails( JITA, "Atreen", (double) Math.round( 3.124297159600001E8 ) ) )
               .thenReturn(
                       jitaFreightDetails );
        Mockito.when( overheadCalculator.getFreightDetails( "Atreen", JITA, (double) Math.round( 345000000d ) ) )
               .thenReturn(
                       jitaFreightDetails );
        Mockito.when(
                overheadCalculator.getFreightDetails( AMARR, "Atreen", (double) Math.round( 3.124297159600001E8 ) ) )
               .thenReturn( amarrFreightDetails );
        Mockito.when( overheadCalculator.getFreightDetails( "Atreen", AMARR, (double) Math.round( 355000000d ) ) )
               .thenReturn( amarrFreightDetails );
        //</editor-fold>
        //<editor-fold desc="Market Fetcher mocks">
        Mockito.when( marketOrderFetcher.getLowestSellPrice( 20, 1L, 22444 ) )
               .thenReturn( 345000000d );
        Mockito.when( marketOrderFetcher.getLowestSellPrice( 21, 2L, 22444 ) )
               .thenReturn( 355000000d );
        //</editor-fold>
        //<editor-fold desc="Crest Endpoint Mocks">
        Mockito.when( solarSystemRepository.getSolarSystemId( JITA ) )
               .thenReturn( 1L );
        Mockito.when( solarSystemRepository.getSolarSystemId( AMARR ) )
               .thenReturn( 2L );
        Mockito.when( regionRepository.findRegionId( "The Forge" ) )
               .thenReturn( 20L );
        Mockito.when( regionRepository.findRegionId( "Domain" ) )
               .thenReturn( 21L );
        CrestType materialItem = new CrestType();
        materialItem.setName( "Material Stuff" );
        CrestType sleipnir = new CrestType();
        sleipnir.setName( "Sleipnir" );
        /*
        {
      "name": null,
      "quantity": 118,
      "source": "LIVE_MARKET_SELL",
      "cost": 41497.95,
      "adjustedCost": 12349.12,
      "typeid": 11530,
      "maketype": null
    },*/
        Mockito.when( itemTypeRepository.getItemDetails( 11530 ) )
               .thenReturn( materialItem );
        /*
    {
      "name": null,
      "quantity": 294,
      "source": "LIVE_MARKET_SELL",
      "cost": 11119.33,
      "adjustedCost": 4137.41,
      "typeid": 11399,
      "maketype": null
    },*/
        Mockito.when( itemTypeRepository.getItemDetails( 11399 ) )
               .thenReturn( materialItem );
        /*
    {
      "name": null,
      "quantity": 11025,
      "source": "LIVE_MARKET_SELL",
      "cost": 9993.17,
      "adjustedCost": 3705.93,
      "typeid": 11542,
      "maketype": null
    },*/
        Mockito.when( itemTypeRepository.getItemDetails( 11542 ) )
               .thenReturn( materialItem );
        /*
    {
      "name": null,
      "quantity": 441,
      "source": "LIVE_MARKET_SELL",
      "cost": 28784.83,
      "adjustedCost": 11225.93,
      "typeid": 11536,
      "maketype": null
    },*/
        Mockito.when( itemTypeRepository.getItemDetails( 11536 ) )
               .thenReturn( materialItem );
        /*
    {
      "name": null,
      "quantity": 1764,
      "source": "LIVE_MARKET_SELL",
      "cost": 39476.44,
      "adjustedCost": 23860.33,
      "typeid": 11538,
      "maketype": null
    },*/
        Mockito.when( itemTypeRepository.getItemDetails( 11538 ) )
               .thenReturn( materialItem );
        /*
    {
      "name": null,
      "quantity": 559,
      "source": "LIVE_MARKET_SELL",
      "cost": 38099.89,
      "adjustedCost": 9737.91,
      "typeid": 11555,
      "maketype": null
    },*/
        Mockito.when( itemTypeRepository.getItemDetails( 11555 ) )
               .thenReturn( materialItem );
        /*
    {
      "name": null,
      "quantity": 294,
      "source": "LIVE_MARKET_SELL",
      "cost": 14720.15,
      "adjustedCost": 4520.0,
      "typeid": 3828,
      "maketype": null
    },*/
        Mockito.when( itemTypeRepository.getItemDetails( 3828 ) )
               .thenReturn( materialItem );
        /*
    {
      "name": null,
      "quantity": 23,
      "source": "LIVE_MARKET_SELL",
      "cost": 618.71,
      "adjustedCost": 438.86,
      "typeid": 11478,
      "maketype": null
    },*/
        Mockito.when( itemTypeRepository.getItemDetails( 11478 ) )
               .thenReturn( materialItem );
        /*
    {
      "name": null,
      "quantity": 59,
      "source": "LIVE_MARKET_SELL",
      "cost": 125464.98,
      "adjustedCost": 18539.86,
      "typeid": 11548,
      "maketype": null
    },*/
        Mockito.when( itemTypeRepository.getItemDetails( 11548 ) )
               .thenReturn( materialItem );
        /*
    {
      "name": null,
      "quantity": 1,
      "source": "LIVE_MARKET_SELL",
      "cost": 5.299999899E7,
      "adjustedCost": 3.192696559E7,
      "typeid": 24702,
      "maketype": null
    },*/
        Mockito.when( itemTypeRepository.getItemDetails( 24702 ) )
               .thenReturn( materialItem );
        /*
    {
      "name": null,
      "quantity": 956,
      "source": "LIVE_MARKET_SELL",
      "cost": 40899.99,
      "adjustedCost": 38666.81,
      "typeid": 11551,
      "maketype": null
    }*/
        Mockito.when( itemTypeRepository.getItemDetails( 11551 ) )
               .thenReturn( materialItem );
        /*
        {
        "name": null,
        "quantity": 16,
        "source": "LIVE_MARKET_SELL",
        "cost": 43694.17732484076,
        "adjustedCost": 39358.3,
        "typeid": 20424,
        "maketype": null
      },*/
        Mockito.when( itemTypeRepository.getItemDetails( 20424 ) )
               .thenReturn( materialItem );
        /*
      {
        "name": null,
        "quantity": 16,
        "source": "LIVE_MARKET_SELL",
        "cost": 100896.99,
        "adjustedCost": 77503.84,
        "typeid": 20172,
        "maketype": null
      }*/
        Mockito.when( itemTypeRepository.getItemDetails( 20172 ) )
               .thenReturn( materialItem );
        Mockito.when( itemTypeRepository.getItemDetails( 22444 ) )
               .thenReturn( sleipnir );
        //</editor-fold>

        BuildCalculationResult result = this.service.calculateBuildCosts( "Atreen", 22444, industrySkills,
                                                                          inventionSkills, 2, 4, null, false,
                                                                          false );

        //<editor-fold desc="Assertions">
        Assert.assertEquals( result.getSeconds()
                                                   .doubleValue() / 60 / 60 / 2 * 200000, result.getSalaryCost(),
                             0.01 );
        Assert.assertNotNull( result.getInventionResult() );
        Assert.assertEquals( ( inventionCalculationResult.getSalaryCost() / 60 / 60 / 40 ) * 200000,
                             result.getInventionResult()
                                   .getSalaryCost(), 0.01 );
        Assert.assertEquals( 20036519.59, result.getInstallationFees(), 0.01 );
        Assert.assertEquals( 2003651.95, result.getInstallationTax(), 0.01 );
        Assert.assertNotNull( result.getFromBuildLocationFreight() );
        Assert.assertNotNull( result.getToBuildLocationFreight() );

        Assert.assertFalse( result.getToBuildLocationFreight()
                                                  .isEmpty() );
        Assert.assertFalse( result.getFromBuildLocationFreight()
                                                  .isEmpty() );
        Assert.assertEquals( jitaFreightDetails, result.getToBuildLocationFreight()
                                                                       .get( JITA ) );
        Assert.assertEquals( amarrFreightDetails, result.getToBuildLocationFreight()
                                                                        .get( AMARR ) );
        Assert.assertEquals( jitaFreightDetails, result.getFromBuildLocationFreight()
                                                                       .get( JITA ) );
        Assert.assertEquals( amarrFreightDetails, result.getFromBuildLocationFreight()
                                                                        .get( AMARR ) );
        Assert.assertEquals( 6000000.0, result.getToBuildLocationFreight()
                                                              .get( AMARR )
                                                              .getCharge(), 0.01 );
        Assert.assertEquals( 1.3E7, result.getToBuildLocationFreight()
                                                          .get( JITA )
                                                          .getCharge(), 0.01 );
        Assert.assertEquals( 6000000.0, result.getFromBuildLocationFreight()
                                                              .get( AMARR )
                                                              .getCharge(), 0.01 );
        Assert.assertEquals( 1.3E7, result.getFromBuildLocationFreight()
                                                          .get( JITA )
                                                          .getCharge(), 0.01 );
        Assert.assertEquals( 136170.0, result.getInventionResult()
                                                             .getSeconds(), 0.01 );
        Assert.assertEquals( "Sleipnir", result.getProductName() );
        //</editor-fold>
    }

}
