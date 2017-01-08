/*
 * Copyright 2016 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.aba.industry.fetch.service.impl;

import com.aba.industry.fetch.client.BuildRequirementsProvider;
import com.aba.industry.fetch.model.*;
import com.aba.industry.model.ActivityMaterialWithCost;
import com.aba.industry.model.Decryptor;
import com.aba.industry.model.TechLevel;
import com.aba.industry.model.fuzzysteve.BlueprintData;
import com.aba.industry.model.fuzzysteve.BlueprintDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Created by maurerit on 7/30/16.
 */
public class StaticDataExportBlueprintYamlService implements BuildRequirementsProvider {
    private Blueprints blueprints;
    private ObjectMapper mapper = new ObjectMapper();

    public StaticDataExportBlueprintYamlService ( ) throws IOException
    {

        Yaml yaml = new Yaml();
        InputStream bpsIS = StaticDataExportBlueprintYamlService.class.getResourceAsStream( "/blueprints.yaml" );
        blueprints = yaml.loadAs( bpsIS, Blueprints.class );
        blueprints.setBlueprints( new ConcurrentHashMap<>( blueprints.getBlueprints() ) );
    }

    @Override
    public BlueprintData getBlueprintData ( final Integer typeId )
    {
        BlueprintData result;

        result = mapBlueprints( typeId );

        return result;
    }

    @Override
    public List<BlueprintData> getAllBlueprints ( )
    {
        final List<BlueprintData> result = new ArrayList<>();
        blueprints.getBlueprints()
                  .entrySet()
                  .stream()
                  .filter( entry -> {
                      return entry.getValue()
                                  .getActivities()
                                  .get( BlueprintActivities.manufacturing ) != null && entry.getValue()
                                                                                            .getActivities()
                                                                                            .get( BlueprintActivities
                                                                                                          .manufacturing )
                                                                                            .getMaterials() != null;
                  } )
                  .map( Map.Entry::getValue )
                  .collect( Collectors.toList() )
                  .forEach( entry -> {
                      if ( entry.getActivities()
                                .get( BlueprintActivities
                                              .manufacturing )
                                .getProducts() != null ) {
                          result.add( mapBlueprints( entry.getActivities()
                                                          .get( BlueprintActivities.manufacturing )
                                                          .getProducts()
                                                          .stream()
                                                          .findFirst()
                                                          .get()
                                                          .getTypeID() ) );
                      }
                  } );


        return result;
    }

    //TODO: This method grew too large, prune it down
    private BlueprintData mapBlueprints ( Integer typeId )
    {
        BlueprintData result = new BlueprintData();

        Blueprint productionBp;
        Blueprint inventionBp = null;

        //region Production BP Lambda
        Optional<Map.Entry<Integer, Blueprint>> possibleMatch = blueprints.getBlueprints()
                                                                          .entrySet()
                                                                          .stream()
                                                                          .filter( entry -> {
                                                                           if ( entry.getValue()
                                                                                     .getActivities()
                                                                                     .containsKey(
                                                                                             BlueprintActivities
                                                                                                     .manufacturing ) &&
                                                                                   entry.getValue()
                                                                                        .getActivities()
                                                                                        .get( BlueprintActivities
                                                                                                      .manufacturing )
                                                                                        .getProducts()
                                                                                           != null ) {
                                                                               return entry.getValue()
                                                                                           .getActivities()
                                                                                           .get(
                                                                                                   BlueprintActivities.manufacturing )
                                                                                           .getProducts()
                                                                                           .stream()
                                                                                           .filter(
                                                                                                   products ->
                                                                                                           products.getTypeID()
                                                                                                                   .equals(
                                                                                                                           typeId ) )
                                                                                           .findFirst()
                                                                                           .isPresent();
                                                                           }

                                                                           return false;
                                                                       } )
                                                                          .findFirst();
        //endregion

        if ( !possibleMatch.isPresent() ) {
            throw new NoSuchElementException( "Type with type id: " + typeId + " has no blueprint." );
        }
        else {
            productionBp = possibleMatch.get()
                                        .getValue();
        }

        final int manuBpTypeId = productionBp.getBlueprintTypeID();

        //region Possible base bp lambda
        possibleMatch = blueprints.getBlueprints()
                                  .entrySet()
                                  .stream()
                                  .filter( entry -> {
                                      if ( entry.getValue()
                                                .getActivities()
                                                .containsKey(
                                                        BlueprintActivities
                                                                .invention ) &&
                                              entry.getValue()
                                                   .getActivities()
                                                   .get( BlueprintActivities
                                                                 .invention )
                                                   .getProducts()
                                                      != null ) {
                                          return entry.getValue()
                                                      .getActivities()
                                                      .get(
                                                              BlueprintActivities.invention )
                                                      .getProducts()
                                                      .stream()
                                                      .filter(
                                                              products ->
                                                                      products.getTypeID()
                                                                              .equals(
                                                                                      manuBpTypeId ) )
                                                      .findFirst()
                                                      .isPresent();
                                      }

                                      return false;
                                  } )
                                  .findFirst();
        //endregion

        if ( possibleMatch.isPresent() ) {
            inventionBp = possibleMatch.get()
                                       .getValue();
        }

        result.setRequestedId( typeId );
        result.setBlueprintSkills( new HashMap<>() );

        //region BlueprintDetails buildup
        BlueprintDetails bpDetails = new BlueprintDetails();

        bpDetails.setMaxProductionLimit( productionBp.getMaxProductionLimit() );
        bpDetails.setProductTypeId( productionBp.getActivities()
                                                .get( BlueprintActivities.manufacturing )
                                                .getProducts()
                                                .get( 0 )
                                                .getTypeID() );
        bpDetails.setProductQuantity( productionBp.getActivities()
                                                  .get( BlueprintActivities.manufacturing )
                                                  .getProducts()
                                                  .get( 0 )
                                                  .getQuantity() );
        //region Times
        bpDetails.setTimesInSeconds( new HashMap<>() );
        if ( productionBp.getActivities()
                         .get( BlueprintActivities.copying ) != null ) {
            bpDetails.getTimesInSeconds()
                     .put( BlueprintActivities.copying.getIndustryActivity()
                                                      .getActivityId(), productionBp.getActivities()
                                                                                    .get( BlueprintActivities.copying )
                                                                                    .getTime() );
        }
        if ( productionBp.getActivities()
                         .get( BlueprintActivities.research_material ) != null ) {
            bpDetails.getTimesInSeconds()
                     .put( BlueprintActivities.research_material.getIndustryActivity()
                                                                .getActivityId(), productionBp.getActivities()
                                                                                              .get( BlueprintActivities.research_material )
                                                                                              .getTime() );
        }
        if ( productionBp.getActivities()
                         .get( BlueprintActivities.research_time ) != null ) {
            bpDetails.getTimesInSeconds()
                     .put( BlueprintActivities.research_time.getIndustryActivity()
                                                            .getActivityId(), productionBp.getActivities()
                                                                                          .get( BlueprintActivities
                                                                                                        .research_time )
                                                                                          .getTime() );
        }
        if ( productionBp.getActivities()
                         .get( BlueprintActivities.manufacturing ) != null ) {
            bpDetails.getTimesInSeconds()
                     .put( BlueprintActivities.manufacturing.getIndustryActivity()
                                                            .getActivityId(), productionBp.getActivities()
                                                                                          .get( BlueprintActivities
                                                                                                        .manufacturing )

                                                                                          .getTime() );
        }
        //endregion

        if ( inventionBp != null ) {
            bpDetails.getTimesInSeconds()
                     .put( BlueprintActivities.invention.getIndustryActivity()
                                                        .getActivityId(), inventionBp.getActivities()
                                                                                     .get( BlueprintActivities
                                                                                                   .invention )
                                                                                     .getTime() );
            Optional<BlueprintOutput> inventionOutput = inventionBp.getActivities()
                                                                   .get( BlueprintActivities.invention )
                                                                   .getProducts()
                                                                   .stream()
                                                                   .filter(
                                                                           products ->
                                                                                   products.getTypeID()
                                                                                           .equals(
                                                                                                   manuBpTypeId ) )
                                                                   .findFirst();

            if ( inventionOutput.isPresent() ) {
                bpDetails.setInventionResultingRuns( inventionOutput.get()
                                                                    .getQuantity() );
            }

            bpDetails.setTechLevel( TechLevel.ADVANCED.getNumerical() );
            if ( inventionBp.getActivities().get( BlueprintActivities.manufacturing ) != null ) {
                bpDetails.setPrecursorTypeId( inventionBp.getActivities()
                                                         .get( BlueprintActivities.manufacturing )
                                                         .getProducts()
                                                         .get( 0 )
                                                         .getTypeID() );
            }
            else {
                //TODO: I don't know if this is correct but it will at least give us SOMETHING with a cost later.
                bpDetails.setPrecursorTypeId( inventionBp.getBlueprintTypeID() );
            }
            bpDetails.setBaseProbability( inventionBp.getActivities()
                                                     .get( BlueprintActivities.invention )
                                                     .getProducts()
                                                     .get( 0 )
                                                     .getProbability() );
        }
        else {
            bpDetails.setTechLevel( TechLevel.TECH_ONE.getNumerical() );
        }
        result.setBlueprintDetails( bpDetails );
        //endregion

        //region Blueprint Skills buildup
        //TODO: Build up the skills in a later release
        //endregion

        //region ActivityMaterialsWithCost buildup
        result.setActivityMaterials( new HashMap<>() );
        result.getActivityMaterials()
              .put( BlueprintActivities.manufacturing.getIndustryActivity()
                                                     .getActivityId(), new ArrayList<>() );
        for ( BlueprintMaterial bpMaterial : productionBp.getActivities()
                                                         .get( BlueprintActivities.manufacturing )
                                                         .getMaterials() ) {
            ActivityMaterialWithCost activityMaterialWithCost = new ActivityMaterialWithCost();
            //TODO: Set the materials blueprint type id in a later release
//            activityMaterialWithCost.setBlueprintTypeId(  );
            //TODO: Set the activity material name in a later release
//            activityMaterialWithCost.setName(  );
            activityMaterialWithCost.setQuantity( bpMaterial.getQuantity() );
            activityMaterialWithCost.setTypeId( bpMaterial.getTypeID() );

            result.getActivityMaterials()
                  .get( BlueprintActivities.manufacturing.getIndustryActivity()
                                                         .getActivityId() )
                  .add( activityMaterialWithCost );
        }

        if ( inventionBp != null ) {
            result.getActivityMaterials()
                  .put( BlueprintActivities.invention.getIndustryActivity()
                                                     .getActivityId(), new ArrayList<>() );

            for ( BlueprintMaterial bpMaterial : inventionBp.getActivities()
                                                            .get( BlueprintActivities.invention )
                                                            .getMaterials() ) {
                ActivityMaterialWithCost activityMaterialWithCost = new ActivityMaterialWithCost();
                //TODO: Set the materials blueprint type id in a later release
//            activityMaterialWithCost.setBlueprintTypeId(  );
                //TODO: Set the activity material name in a later release
//            activityMaterialWithCost.setName(  );
                activityMaterialWithCost.setQuantity( bpMaterial.getQuantity() );
                activityMaterialWithCost.setTypeId( bpMaterial.getTypeID() );

                result.getActivityMaterials()
                      .get( BlueprintActivities.invention.getIndustryActivity()
                                                         .getActivityId() )
                      .add( activityMaterialWithCost );
            }
        }
        //endregion

        try {
            result.setDecryptors( this.getDecryptors() );
        }
        catch ( IOException e ) {
            //TODO: Handle decryptors not deserializing properly in a later release
            //This is a bit annoying, but I'm ignoring it for now.
        }
        return result;
    }

    private List<Decryptor> getDecryptors ( ) throws IOException
    {
        InputStream decryptorIS = StaticDataExportBlueprintYamlService.class.getResourceAsStream(
                "/decryptors.json" );
        TypeFactory typeFactory = mapper.getTypeFactory();
        CollectionType listType = typeFactory.constructCollectionType( ArrayList.class, Decryptor.class );

        List<Decryptor> decryptors = mapper.readValue( decryptorIS, listType );

        return decryptors;
    }
}
