package com.aba.industry.config;

import com.aba.data.domain.config.FreightConfiguration;
import com.aba.data.domain.config.SalaryConfiguration;

public interface OverheadConfigurationService {
	SalaryConfiguration getSalaryConfiguration ( );
	
	FreightConfiguration getFreightConfiguration ( );
}
