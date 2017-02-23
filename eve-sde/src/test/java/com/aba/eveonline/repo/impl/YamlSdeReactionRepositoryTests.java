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

import com.aba.eveonline.model.Reaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by maurerit on 2/11/17.
 */
@RunWith( JUnit4.class )
public class YamlSdeReactionRepositoryTests {
    private YamlSdeReactionRepository reactionRepository = new YamlSdeReactionRepository(
            new YamlSdeItemTypeRepository() );

    @Test
    public void testGetHypersynapticFibersReactionByOutputName ( ) {
        Reaction hyperSynFibers = reactionRepository.findByReactionName( "Hypersynaptic Fibers" );

        assertEquals( 1, hyperSynFibers.getOutputs()
                                      .size() );
    }

    @Test
    public void testGetHyperSynapticFibersReactionByReactionTypeId ( ) {
        Reaction hyperSynFibers = reactionRepository.findByReactionTypeId(17970l);

        assertEquals("Hypersynaptic Fibers Reaction",hyperSynFibers.getReaction().getName().getEn());
    }

    @Test
    public void testGetAllReactionsWithSoleriumAsInput ( ) {
        List<Reaction> reactions = reactionRepository.findAllByInput("Solerium");

        assertEquals(1, reactions.size());
        assertEquals("Hypersynaptic Fibers Reaction", reactions.get(0).getReaction().getName().getEn());
    }

    @Test
    public void testGetAllReactionsWithCadmiumAsInput ( ) {
        List<Reaction> reactions = reactionRepository.findAllByInput("Cadmium");

        assertEquals(7, reactions.size());
    }

    @Test
    public void findAll ( ) {
        List<Reaction> reactions = reactionRepository.findAll();

        assertEquals(103, reactions.size());
    }
}
