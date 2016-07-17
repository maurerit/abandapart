package com.aba.industry.fetch.client;

import java.io.IOException;

import com.aba.industry.model.fuzzysteve.SystemCostIndexes;

public interface CostIndexProvider {
	SystemCostIndexes getSystemCostIndexes ( String systemName ) throws IOException;
}
