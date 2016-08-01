/*
 * Copyright 2016 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.aba.industry.fetch.client;

import com.aba.industry.model.fuzzysteve.BlueprintData;

import java.io.IOException;

/**
 * @author maurerit
 */
public interface BuildRequirementsProvider {
    /**
     * This method originate from Fuzzy Steve's blueprint calculator and how it gathers
     * the data it needs.  The data structure was quite nice and included everything that is
     * needed to determine all the times and materials along with skills needed.  Sort
     * of a one stop shop so I just stole the structure and (at least temporarily) will consume
     * his data until I have the time to create my own.
     *
     * @param typeId The type id of the product to be produced
     * @return A {@link BlueprintData} object instance or null if nothing was found.
     * @throws IOException This method consumes data from the web and could run into issue's
     *                     be prepared.
     */
    BlueprintData getBlueprintData ( Integer typeId ) throws IOException;
}
