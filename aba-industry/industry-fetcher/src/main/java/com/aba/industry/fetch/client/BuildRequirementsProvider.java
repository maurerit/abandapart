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
     * @param typeId
     * @return A {@link BlueprintData} object instance or null if nothing was found.
     * @throws IOException This method consumes data from the web and could run into issue's
     * be prepared.
     */
    BlueprintData getBlueprintData ( Long typeId ) throws IOException;
}
