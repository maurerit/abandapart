/*
 * Copyright 2016 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
 * the specific language governing permissions and limitations under the License.
 */
package com.aba.eveonline.repo.impl;

import com.aba.eveonline.model.Type;
import com.aba.eveonline.repo.ItemTypeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class YamlItemTypeRepositoryTests {
    private ItemTypeRepository repo = new YamlSdeItemTypeRepository();

    @Test
    public void printMemoryStuff ( ) {
        int mb = 1024*1024;

        //Getting the runtime reference from system
        Runtime runtime = Runtime.getRuntime();

        runtime.gc();

        System.out.println("##### Heap utilization statistics [MB] #####");

        //Print used memory
        System.out.println("Used Memory:"
                + (runtime.totalMemory() - runtime.freeMemory()) / mb);

        //Print free memory
        System.out.println("Free Memory:"
                + runtime.freeMemory() / mb);

        //Print total available memory
        System.out.println("Total Memory:" + runtime.totalMemory() / mb);

        //Print Maximum available memory
        System.out.println("Max Memory:" + runtime.maxMemory() / mb);
    }

    @Test
    public void find150mmRailgunIIByTypeId ( ) {
        Type type = this.repo.findByTypeId(3074);

        assertEquals(183136.0, type.getBasePrice(), 0.0);
        assertEquals(0.1, type.getCapacity(), 0.0);
        assertEquals(new Integer(74), type.getGroupID());
        assertEquals(new Integer(349), type.getIconID());
        assertEquals(new Integer(564), type.getMarketGroupID());
        assertEquals(500, type.getMass(), 0.0);
        assertEquals("150mm Railgun II", type.getName().getEn());
        assertEquals(new Integer(1), type.getPortionSize());
        assertEquals(true, type.getPublished());
        assertEquals(10.0, type.getRadius(), 0.0);
        assertEquals("laidai", type.getSofFactionName());
        assertEquals(5.0, type.getVolume(), 0.0);
    }

    @Test
    public void find150mmRailgunIIByName ( ) {
        Type type = this.repo.findByName("150mm Railgun II");

        assertEquals(183136.0, type.getBasePrice(), 0.0);
        assertEquals(0.1, type.getCapacity(), 0.0);
        assertEquals(new Integer(74), type.getGroupID());
        assertEquals(new Integer(349), type.getIconID());
        assertEquals(new Integer(564), type.getMarketGroupID());
        assertEquals(500, type.getMass(), 0.0);
        assertEquals("150mm Railgun II", type.getName().getEn());
        assertEquals(new Integer(1), type.getPortionSize());
        assertEquals(true, type.getPublished());
        assertEquals(10.0, type.getRadius(), 0.0);
        assertEquals("laidai", type.getSofFactionName());
        assertEquals(5.0, type.getVolume(), 0.0);
    }
}
