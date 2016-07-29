package com.aba.industry.service.impl;

import com.aba.data.domain.config.ConfigurationType;
import com.aba.data.domain.config.IndustrySkillConfiguration;
import com.aba.data.domain.config.InventionSkillConfiguration;
import com.aba.eveonline.crest.repo.RegionRepository;
import com.aba.eveonline.crest.repo.SolarSystemRepository;
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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;
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
                                   .get( Activity.INVENTION.getActivityId() );
        manufacturingMaterials = bpData.getActivityMaterials()
                                       .get( Activity.MANUFACTURING.getActivityId() );

        bpData.getActivityMaterials()
              .clear();

        for ( ActivityMaterialWithCost am : inventionMaterials ) {
            ItemCost ic = itemCosts.get( am.getTypeId()
                                           .intValue() );

            am.setCost( ic.getSell() );
            am.setAdjustedCost( ic.getAdjusted() );
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

    //TODO: NullPointers should be wrapped
    @Test( expected = NullPointerException.class )
    public void testThatInventionCalcThrowsNullPointer ( ) throws IOException
    {
        Mockito.when( buildRequirementsProvider.getBlueprintData( 22444l ) )
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

        BuildCalculationResult result = industryCalculationService.calculateBuildCosts( "Atreen", 22444l,
                                                                                        industrySkills,
                                                                                        inventionSkills, 2, 4, null,
                                                                                        false,
                                                                                        false );
    }
}
