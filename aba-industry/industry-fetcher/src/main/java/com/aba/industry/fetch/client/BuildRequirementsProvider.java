package com.aba.industry.fetch.client;

import com.aba.industry.model.fuzzysteve.BlueprintData;

import java.io.IOException;

/**
 * @author maurerit
 */
public interface BuildRequirementsProvider {
    BlueprintData getBlueprintData ( Long typeId ) throws IOException
}
