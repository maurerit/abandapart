package com.aba.industry.config;

import java.util.List;

import com.aba.data.domain.config.BuildOrBuyConfiguration;

public interface BuildOrBuyConfigurationService {
	List<BuildOrBuyConfiguration> getAllBuildOrBuyConfigurations ( );
	
	void createOrUpdateBuildOrBuyConfiguration ( BuildOrBuyConfiguration config );
}
