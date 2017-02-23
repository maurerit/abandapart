/*
 * Copyright 2017 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.aba.eveonline.repo.impl;

import com.aba.eveonline.model.*;
import com.aba.eveonline.repo.ItemTypeRepository;
import com.aba.eveonline.repo.ReactionRepository;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by maurerit on 2/10/17.
 */
public class YamlSdeReactionRepository implements ReactionRepository {
    private ItemTypeRepository itemTypeRepository;
    private List<Reaction> reactions = new ArrayList<>();

    public YamlSdeReactionRepository ( ItemTypeRepository itemTypeRepository ) {
        this.itemTypeRepository = itemTypeRepository;
        Yaml yaml = new Yaml();
        InputStream reactionIS = YamlSdeReactionRepository.class.getResourceAsStream( "/invTypeReactions.yaml" );
        ReactionRecords reactionRecords = yaml.loadAs( reactionIS, ReactionRecords.class );
        loadReactions( reactionRecords );
    }

    private void loadReactions ( ReactionRecords reactionRecords ) {
        List<Long> reactionTypeIds = reactionRecords.getReactions()
                                                    .stream()
                                                    .mapToLong( ReactionRecord::getReactionTypeID )
                                                    .distinct()
                                                    .boxed()
                                                    .collect( Collectors.toList() );

        for ( Long reactionTypeId : reactionTypeIds ) {
            List<ReactionRecord> reactionInputs = reactionRecords.getReactions()
                                                                 .stream()
                                                                 .filter(
                                                                         rr -> rr.getReactionTypeID()
                                                                                 .equals(
                                                                                         reactionTypeId ) && rr
                                                                                 .getInput() )
                                                                 .collect(

                                                                         Collectors.toList() );
            List<ReactionRecord> reactionOutputs = reactionRecords.getReactions()
                                                                  .stream()
                                                                  .filter(
                                                                          rr -> rr.getReactionTypeID()
                                                                                  .equals( reactionTypeId )
                                                                                  && !rr.getInput() )
                                                                  .collect( Collectors.toList() );

            Reaction reaction = new Reaction();

            for ( ReactionRecord record : reactionInputs ) {
                Type inputType = itemTypeRepository.findByTypeId( record.getTypeID() );
                ReactionInputOutput io = new ReactionInputOutput();
                io.setType( inputType );
                io.setQuantity( record.getQuantity() );

                reaction.getInputs()
                        .add( io );
            }

            for ( ReactionRecord record : reactionOutputs ) {
                Type outputType = itemTypeRepository.findByTypeId( record.getTypeID() );
                ReactionInputOutput io = new ReactionInputOutput();
                io.setType( outputType );
                io.setQuantity( record.getQuantity() );

                reaction.getOutputs()
                        .add( io );
            }

            reaction.setReaction(itemTypeRepository.findByTypeId(reactionTypeId));

            this.reactions.add( reaction );
        }
    }

    @Override
    public List<Reaction> findAll ( ) {
        return this.reactions;
    }

    @Override
    public Reaction findByReactionName ( String reactionName ) {
        Reaction foundReaction = null;

        Optional<Reaction> possibleReaction = this.reactions.stream()
                                                            .filter( reaction -> reaction.getOutputs()
                                                                                         .stream()
                                                                                         .anyMatch(
                                                                                                 output -> output
                                                                                                         .getType()
                                                                                                         .getName()
                                                                                                         .equalsIgnoreCase(
                                                                                                                 reactionName ) )
                                                            )
                                                            .findFirst();

        if ( possibleReaction.isPresent() ) {
            foundReaction = possibleReaction.get();
        }

        return foundReaction;
    }

    @Override
    public Reaction findByReactionTypeId ( Long reactionTypeId ) {
        Reaction foundReaction = null;

        Optional<Reaction> possibleReaction = this.reactions.stream().filter(reaction -> reactionTypeId.equals(reaction.getReaction().getTypeId())).findFirst();

        if ( possibleReaction.isPresent() ) {
            foundReaction = possibleReaction.get();
        }

        return foundReaction;
    }

    @Override
    public List<Reaction> findAllByInput ( String inputName ) {
        List<Reaction> foundReactions = reactions.stream()
                                                 .filter( reaction -> reaction.getInputs()
                                                                              .stream()
                                                                              .filter(
                                                                                      input -> input
                                                                                              .getType()
                                                                                              .getName()
                                                                                              .equalsIgnoreCase(inputName))
                                                                                              .count() > 0
                                                 )
                                                 .collect(Collectors.toList());

        return foundReactions;
    }
}
