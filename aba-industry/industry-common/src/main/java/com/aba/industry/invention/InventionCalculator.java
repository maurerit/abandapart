package com.aba.industry.invention;

import java.util.List;

import com.aba.data.domain.config.InventionSkillConfiguration;
import com.aba.industry.model.ActivityMaterialWithCost;
import com.aba.industry.model.Decryptor;
import com.aba.industry.model.InventionCalculationResult;
import com.aba.industry.model.fuzzysteve.BlueprintDetails;
import com.aba.industry.model.fuzzysteve.SystemCostIndexes;

/**
 * Represents the algorithm used by CCP to calculate invention costs.  Implementations CAN do more
 * but they're spec'ed out with only the ability to do the calculation.  Something else should gather
 * the costs for the required inputs and other stuff.
 * 
 * @author maurerit
 */
public interface InventionCalculator {
	InventionCalculationResult calculateInventionCosts (
			SystemCostIndexes costIndexes,
			BlueprintDetails bpDetails,
			Decryptor decryptor,
			List<ActivityMaterialWithCost> amWithCost,
			InventionSkillConfiguration skillConfiguration );
}
