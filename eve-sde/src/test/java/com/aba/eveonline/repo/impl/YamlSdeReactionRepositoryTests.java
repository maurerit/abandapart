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

import static org.junit.Assert.assertEquals;

/**
 * Created by maurerit on 2/11/17.
 */
@RunWith( JUnit4.class )
public class YamlSdeReactionRepositoryTests {
    private YamlSdeReactionRepository reactionRepository = new YamlSdeReactionRepository(
            new YamlSdeItemTypeRepository() );

    @Test
    public void testGetHypersynapticFibersReaction ( ) {
        Reaction hperSynFibers = reactionRepository.findByReactionName( "Hypersynaptic Fibers" );

        assertEquals( 1, hperSynFibers.getOutput()
                                      .size() );
    }
}
