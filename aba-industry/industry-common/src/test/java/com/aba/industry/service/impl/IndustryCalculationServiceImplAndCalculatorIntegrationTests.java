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
import com.aba.industry.invention.impl.InventionCalculatorImpl;
import com.aba.industry.manufacturing.ManufacturingCalculator;
import com.aba.industry.manufacturing.impl.ManufacturingCalculatorImpl;
import com.aba.industry.model.*;
import com.aba.industry.model.fuzzysteve.BlueprintData;
import com.aba.industry.model.fuzzysteve.SystemCostIndexes;
import com.aba.industry.overhead.OverheadCalculator;
import com.aba.industry.overhead.impl.LHOverheadCalculatorImpl;
import com.aba.market.fetch.MarketOrderFetcher;
import com.aba.market.fetch.MarketPriceFetcher;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.devfleet.crest.model.CrestType;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by maurerit on 7/28/2016.
 */
@RunWith( MockitoJUnitRunner.class )
public class IndustryCalculationServiceImplAndCalculatorIntegrationTests {
    @Mock
    BuildRequirementsProvider    buildRequirementsProvider;
    @Mock
    CostIndexProvider            costIndexProvider;
    @Mock
    OverheadConfigurationService overheadConfigurationService;
    @Mock
    RegionRepository             regionRepository;
    @Mock
    MarketOrderFetcher           marketOrderFetcher;
    @Mock
    MarketPriceFetcher           marketPriceFetcher;
    @Mock
    ItemTypeRepository           itemTypeRepository;
    @InjectMocks
    private IndustryCalculationServiceImpl industryCalculationService = new IndustryCalculationServiceImpl();
    private InventionCalculator            inventionCalculator        = new InventionCalculatorImpl();
    private ManufacturingCalculator        manufacturingCalculator    = new ManufacturingCalculatorImpl();
    private OverheadCalculator             overheadCalculator         = new LHOverheadCalculatorImpl();
    @Mock
    private SolarSystemRepository solarSystemRepository;
    private ObjectMapper mapper = new ObjectMapper();
    private MapType                        mapType;
    private Map<Integer, ItemCost>         itemCosts;
    private SystemCostIndexes              costIndexes;
    private BlueprintData                  bpData;
    private IndustrySkillConfiguration     industrySkills;
    private InventionSkillConfiguration    inventionSkills;
    private InventionCalculationResult     inventionCalculationResult;
    private BuildCalculationResult         buildCalculationResult;
    private FreightDetails                 jitaFreightDetails;
    private FreightDetails                 amarrFreightDetails;
    private List<ActivityMaterialWithCost> inventionMaterials;
    private List<ActivityMaterialWithCost> manufacturingMaterials;

    @Before
    public void setupDependencies ( )
    {
        industryCalculationService.setInventionCalc( inventionCalculator );
        industryCalculationService.setManufacturingCalc( manufacturingCalculator );
        industryCalculationService.setOverheadCalculator( overheadCalculator );
    }

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

        inventionMaterials = bpData.getActivityMaterials()
                                   .get( IndustryActivities.INVENTION.getActivityId() );
        manufacturingMaterials = bpData.getActivityMaterials()
                                       .get( IndustryActivities.MANUFACTURING.getActivityId() );

        bpData.getActivityMaterials()
              .clear();

        bpData.getActivityMaterials()
              .put( IndustryActivities.INVENTION.getActivityId(), new ArrayList<>() );
        bpData.getActivityMaterials()
              .put( IndustryActivities.MANUFACTURING.getActivityId(), new ArrayList<>() );
        bpData.getBlueprintDetails()
              .setPrecursorAdjustedPrice( null );

        for ( ActivityMaterialWithCost am : inventionMaterials ) {
            ItemCost ic = itemCosts.get( am.getTypeId()
                                           .intValue() );
            am.setCost( ic.getSell() );
            am.setAdjustedCost( ic.getAdjusted() );

            ActivityMaterialWithCost amwcWithoutCosts = new ActivityMaterialWithCost();
        }

        for ( ActivityMaterialWithCost am : manufacturingMaterials ) {
            ItemCost ic = itemCosts.get( am.getTypeId()
                                           .intValue() );

            am.setCost( ic.getSell() );
            am.setAdjustedCost( ic.getAdjusted() );
        }

        inventionCalculationResult = new InventionCalculationResult();
        inventionCalculationResult.setSeconds( 2000l );

        jitaFreightDetails = new FreightDetails();

        jitaFreightDetails.setCharge( 13000000d );
        jitaFreightDetails.setJumps( 15 );

        amarrFreightDetails = new FreightDetails();

        amarrFreightDetails.setCharge( 6000000d );
        amarrFreightDetails.setJumps( 9 );
    }

    @Before
    public void mockSetupBeforeTests ( ) throws IOException
    {
        Mockito.when( buildRequirementsProvider.getBlueprintData( 22444 ) )
               .thenReturn( this.bpData );
        Mockito.when( costIndexProvider.getSystemCostIndexes( "Atreen" ) )
               .thenReturn( this.costIndexes );
        //<editor-fold desc="Market Fetcher mocks">
        Mockito.when( marketOrderFetcher.getLowestSellPrice( 20, 1l, 22444 ) )
               .thenReturn( 345000000d );
        Mockito.when( marketOrderFetcher.getLowestSellPrice( 21, 2l, 22444 ) )
               .thenReturn( 355000000d );
        //</editor-fold>
        //<editor-fold desc="Crest Endpoint Mocks">
        Mockito.when( solarSystemRepository.getSolarSystemId( "Jita" ) )
               .thenReturn( 1l );
        Mockito.when( solarSystemRepository.getSolarSystemId( "Amarr" ) )
               .thenReturn( 2l );
        Mockito.when( regionRepository.findRegionId( "The Forge" ) )
               .thenReturn( 20l );
        Mockito.when( regionRepository.findRegionId( "Domain" ) )
               .thenReturn( 21l );
        //</editor-fold>
    }

    //TODO: NullPointers should be wrapped
    //TODO: I don't really like this test as it SHOULD never be possible...  Think of something better to do
    @Test( expected = NullPointerException.class )
    @Ignore
    public void testThatInventionCalcThrowsNullPointer ( ) throws IOException
    {
        BuildCalculationResult result = industryCalculationService.calculateBuildCosts( "Atreen", 22444,
                                                                                        industrySkills,
                                                                                        inventionSkills, 2, 4, null,
                                                                                        false,
                                                                                        false );
    }

    @Test
    public void testThatInventionAndManufacturingCalcWork ( )
    {
        //<editor-fold desc="Market Fetcher Mocks">
        CrestType materialItem = new CrestType();
        materialItem.setName( "Material Stuff" );
        CrestType sleipnir = new CrestType();
        sleipnir.setName( "Sleipnir" );
        /*
        {
  "3812": {
    "sell": 141.87,
    "buy": 166.81,
    "adjusted": 0,
    "average": 309.19
  },
        */
        Mockito.when( marketOrderFetcher.getLowestSellPrice( 20, 1l, 3812 ) )
               .thenReturn( 141.87 );
        Mockito.when( marketPriceFetcher.getAdjustedPrice( 3812 ) )
               .thenReturn( 0d );
        Mockito.when( itemTypeRepository.getItemDetails( 3812 ) )
               .thenReturn( materialItem );
        /*
  "3814": {
    "sell": 34.3,
    "buy": 34.83,
    "adjusted": 0,
    "average": 32.94
  },
  */
        Mockito.when( marketOrderFetcher.getLowestSellPrice( 20, 1l, 3814 ) )
               .thenReturn( 34.3 );
        Mockito.when( marketPriceFetcher.getAdjustedPrice( 3814 ) )
               .thenReturn( 0d );
        Mockito.when( itemTypeRepository.getItemDetails( 3814 ) )
               .thenReturn( materialItem );
        /*
  "3828": {
    "sell": 21568,
    "buy": 14029.56,
    "adjusted": 4352.38,
    "average": 11600.52
  },
  */
        Mockito.when( marketOrderFetcher.getLowestSellPrice( 20, 1l, 3828 ) )
               .thenReturn( 21568d );
        Mockito.when( marketPriceFetcher.getAdjustedPrice( 3828 ) )
               .thenReturn( 0d );
        Mockito.when( itemTypeRepository.getItemDetails( 3828 ) )
               .thenReturn( materialItem );
        /*
  "9836": {
    "sell": 15519.89,
    "buy": 14458.92,
    "adjusted": 4000.75,
    "average": 12734.16
  },
  */
        Mockito.when( marketOrderFetcher.getLowestSellPrice( 20, 1l, 9836 ) )
               .thenReturn( 15519.89 );
        Mockito.when( marketPriceFetcher.getAdjustedPrice( 9836 ) )
               .thenReturn( 4000.75 );
        Mockito.when( itemTypeRepository.getItemDetails( 9836 ) )
               .thenReturn( materialItem );
        /*
  "11399": {
    "sell": 12558.04,
    "buy": 11230.57,
    "adjusted": 4045.75,
    "average": 10160.97
  },
  */
        Mockito.when( marketOrderFetcher.getLowestSellPrice( 20, 1l, 11399 ) )
               .thenReturn( 12558.04 );
        Mockito.when( marketPriceFetcher.getAdjustedPrice( 11399 ) )
               .thenReturn( 4045.75 );
        Mockito.when( itemTypeRepository.getItemDetails( 11399 ) )
               .thenReturn( materialItem );
        /*
  "11461": {
    "sell": 389.56,
    "buy": 100.59,
    "adjusted": 1315.61,
    "average": 516.32
  },
  */
        Mockito.when( marketOrderFetcher.getLowestSellPrice( 20, 1l, 11461 ) )
               .thenReturn( 389.56 );
        Mockito.when( marketPriceFetcher.getAdjustedPrice( 11461 ) )
               .thenReturn( 1315.61 );
        Mockito.when( itemTypeRepository.getItemDetails( 11461 ) )
               .thenReturn( materialItem );
        /*
  "11478": {
    "sell": 596.8,
    "buy": 287.12,
    "adjusted": 258.71,
    "average": 257.17
  },
  */
        Mockito.when( marketOrderFetcher.getLowestSellPrice( 20, 1l, 11478 ) )
               .thenReturn( 596.8 );
        Mockito.when( marketPriceFetcher.getAdjustedPrice( 11478 ) )
               .thenReturn( 258.71 );
        Mockito.when( itemTypeRepository.getItemDetails( 11478 ) )
               .thenReturn( materialItem );
        /*
  "11530": {
    "sell": 59498,
    "buy": 41609.95,
    "adjusted": 13173.3,
    "average": 39040.3
  },
  */
        Mockito.when( marketOrderFetcher.getLowestSellPrice( 20, 1l, 11530 ) )
               .thenReturn( 59498.00 );
        Mockito.when( marketPriceFetcher.getAdjustedPrice( 11530 ) )
               .thenReturn( 13173.3 );
        Mockito.when( itemTypeRepository.getItemDetails( 11530 ) )
               .thenReturn( materialItem );
        /*
  "11536": {
    "sell": 28796.99,
    "buy": 17599.39,
    "adjusted": 11086.65,
    "average": 26292.95
  },
  */
        Mockito.when( marketOrderFetcher.getLowestSellPrice( 20, 1l, 11536 ) )
               .thenReturn( 28796.99 );
        Mockito.when( marketPriceFetcher.getAdjustedPrice( 11536 ) )
               .thenReturn( 11086.65 );
        Mockito.when( itemTypeRepository.getItemDetails( 11536 ) )
               .thenReturn( materialItem );
        /*
  "11538": {
    "sell": 39490.16,
    "buy": 34874.15,
    "adjusted": 26546.29,
    "average": 38102.26
  },
  */
        Mockito.when( marketOrderFetcher.getLowestSellPrice( 20, 1l, 11538 ) )
               .thenReturn( 39490.16 );
        Mockito.when( marketPriceFetcher.getAdjustedPrice( 11538 ) )
               .thenReturn( 26546.29 );
        Mockito.when( itemTypeRepository.getItemDetails( 11538 ) )
               .thenReturn( materialItem );
        /*
  "11542": {
    "sell": 9004.98,
    "buy": 8561.01,
    "adjusted": 4022,
    "average": 10334.39
  },
  */
        Mockito.when( marketOrderFetcher.getLowestSellPrice( 20, 1l, 11542 ) )
               .thenReturn( 9004.98 );
        Mockito.when( marketPriceFetcher.getAdjustedPrice( 11542 ) )
               .thenReturn( 4022d );
        Mockito.when( itemTypeRepository.getItemDetails( 11542 ) )
               .thenReturn( materialItem );
        /*
  "11548": {
    "sell": 117999.52,
    "buy": 65863.17,
    "adjusted": 19212.66,
    "average": 124219.57
  },
  */
        Mockito.when( marketOrderFetcher.getLowestSellPrice( 20, 1l, 11548 ) )
               .thenReturn( 117999.52 );
        Mockito.when( marketPriceFetcher.getAdjustedPrice( 11548 ) )
               .thenReturn( 19212.66 );
        Mockito.when( itemTypeRepository.getItemDetails( 11548 ) )
               .thenReturn( materialItem );
        /*
  "11551": {
    "sell": 37498.86,
    "buy": 34216.51,
    "adjusted": 32909,
    "average": 37349.96
  },
  */
        Mockito.when( marketOrderFetcher.getLowestSellPrice( 20, 1l, 11551 ) )
               .thenReturn( 37498.86 );
        Mockito.when( marketPriceFetcher.getAdjustedPrice( 11551 ) )
               .thenReturn( 32909d );
        Mockito.when( itemTypeRepository.getItemDetails( 11551 ) )
               .thenReturn( materialItem );
        /*
  "11555": {
    "sell": 35300,
    "buy": 33974.12,
    "adjusted": 9706.05,
    "average": 38550.94
  },
  */
        Mockito.when( marketOrderFetcher.getLowestSellPrice( 20, 1l, 11555 ) )
               .thenReturn( 35300.00 );
        Mockito.when( marketPriceFetcher.getAdjustedPrice( 35300 ) )
               .thenReturn( 9706.05 );
        Mockito.when( itemTypeRepository.getItemDetails( 11555 ) )
               .thenReturn( materialItem );
        /*
  "20172": {
    "sell": 99998.93,
    "buy": 80001.09,
    "adjusted": 77837.65,
    "average": 108443.91
  },
  */
        Mockito.when( marketOrderFetcher.getLowestSellPrice( 20, 1l, 20172 ) )
               .thenReturn( 99998.93 );
        Mockito.when( marketPriceFetcher.getAdjustedPrice( 20172 ) )
               .thenReturn( 77837.65 );
        Mockito.when( itemTypeRepository.getItemDetails( 20172 ) )
               .thenReturn( materialItem );
        /*
  "20424": {
    "sell": 43609.7,
    "buy": 35327.32,
    "adjusted": 61198.27,
    "average": 55965.93
  },
  */
        Mockito.when( marketOrderFetcher.getLowestSellPrice( 20, 1l, 20424 ) )
               .thenReturn( 43609.7 );
        Mockito.when( marketPriceFetcher.getAdjustedPrice( 20424 ) )
               .thenReturn( 61198.27 );
        Mockito.when( itemTypeRepository.getItemDetails( 20424 ) )
               .thenReturn( materialItem );
        /*
  "24702": {
    "sell": 51178568.63,
    "buy": 47000109.06,
    "adjusted": 34529995.28,
    "average": 56564037.86
  },
  */
        Mockito.when( marketOrderFetcher.getLowestSellPrice( 20, 1l, 24702 ) )
               .thenReturn( 51178568.63 );
        Mockito.when( marketPriceFetcher.getAdjustedPrice( 24702 ) )
               .thenReturn( 34529995.28 );
        Mockito.when( itemTypeRepository.getItemDetails( 24702 ) )
               .thenReturn( materialItem );
        /*
  "34201": {
    "sell": 388669.78,
    "buy": 352205.84,
    "adjusted": 0,
    "average": 323614.58
  },
  */
        Mockito.when( marketOrderFetcher.getLowestSellPrice( 20, 1l, 34201 ) )
               .thenReturn( 388669.78 );
        Mockito.when( marketPriceFetcher.getAdjustedPrice( 34201 ) )
               .thenReturn( 0d );
        Mockito.when( itemTypeRepository.getItemDetails( 34201 ) )
               .thenReturn( materialItem );
        /*
  "34202": {
    "sell": 2789999.98,
    "buy": 799598.28,
    "adjusted": 0,
    "average": 2020783.76
  },
  */
        Mockito.when( marketOrderFetcher.getLowestSellPrice( 20, 1l, 34202 ) )
               .thenReturn( 2789999.98 );
        Mockito.when( marketPriceFetcher.getAdjustedPrice( 34202 ) )
               .thenReturn( 0d );
        Mockito.when( itemTypeRepository.getItemDetails( 34202 ) )
               .thenReturn( materialItem );
        /*
  "34203": {
    "sell": 818745.39,
    "buy": 665080.14,
    "adjusted": 0,
    "average": 870524.16
  },
  */
        Mockito.when( marketOrderFetcher.getLowestSellPrice( 20, 1l, 34203 ) )
               .thenReturn( 818745.39 );
        Mockito.when( marketPriceFetcher.getAdjustedPrice( 34203 ) )
               .thenReturn( 0d );
        Mockito.when( itemTypeRepository.getItemDetails( 34203 ) )
               .thenReturn( materialItem );
        /*
  "34204": {
    "sell": 969103,
    "buy": 876844.99,
    "adjusted": 0,
    "average": 910288.93
  },
  */
        Mockito.when( marketOrderFetcher.getLowestSellPrice( 20, 1l, 34204 ) )
               .thenReturn( 969103d );
        Mockito.when( marketPriceFetcher.getAdjustedPrice( 34204 ) )
               .thenReturn( 0d );
        Mockito.when( itemTypeRepository.getItemDetails( 34204 ) )
               .thenReturn( materialItem );
        /*
  "34205": {
    "sell": 440005,
    "buy": 349648.45,
    "adjusted": 0,
    "average": 225192.06
  },
  */
        Mockito.when( marketOrderFetcher.getLowestSellPrice( 20, 1l, 34205 ) )
               .thenReturn( 440005d );
        Mockito.when( marketPriceFetcher.getAdjustedPrice( 34205 ) )
               .thenReturn( 0d );
        Mockito.when( itemTypeRepository.getItemDetails( 34205 ) )
               .thenReturn( materialItem );
        /*
  "34206": {
    "sell": 269251.17,
    "buy": 213620.31,
    "adjusted": 0,
    "average": 302613.4
  },
  */
        Mockito.when( marketOrderFetcher.getLowestSellPrice( 20, 1l, 34206 ) )
               .thenReturn( 269251.17 );
        Mockito.when( marketPriceFetcher.getAdjustedPrice( 34206 ) )
               .thenReturn( 0d );
        Mockito.when( itemTypeRepository.getItemDetails( 34206 ) )
               .thenReturn( materialItem );
        /*
  "34207": {
    "sell": 3998918.7,
    "buy": 522307.5,
    "adjusted": 0,
    "average": 3249111.93
  },
  */
        Mockito.when( marketOrderFetcher.getLowestSellPrice( 20, 1l, 34207 ) )
               .thenReturn( 3998918.7 );
        Mockito.when( marketPriceFetcher.getAdjustedPrice( 34207 ) )
               .thenReturn( 0d );
        Mockito.when( itemTypeRepository.getItemDetails( 34207 ) )
               .thenReturn( materialItem );
        /*
  "34208": {
    "sell": 4710999.92,
    "buy": 1258279.56,
    "adjusted": 0,
    "average": 4436939.26
  }
  */
        Mockito.when( marketOrderFetcher.getLowestSellPrice( 20, 1l, 34208 ) )
               .thenReturn( 4710999.92 );
        Mockito.when( marketPriceFetcher.getAdjustedPrice( 34208 ) )
               .thenReturn( 0d );
        Mockito.when( itemTypeRepository.getItemDetails( 34208 ) )
               .thenReturn( materialItem );
        /*
}
         */
        Mockito.when( marketPriceFetcher.getAdjustedPrice( 24703 ) )
               .thenReturn( 34958392.2 );
        Mockito.when( itemTypeRepository.getItemDetails( 22444 ) )
               .thenReturn( sleipnir );
        //</editor-fold>
        BuildCalculationResult result = industryCalculationService.calculateBuildCosts( "Atreen", 22444,
                                                                                        industrySkills,
                                                                                        inventionSkills, 2, 4, null,
                                                                                        false,
                                                                                        false );
    }
}
