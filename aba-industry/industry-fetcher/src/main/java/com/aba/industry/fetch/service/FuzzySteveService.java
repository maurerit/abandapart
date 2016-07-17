package com.aba.industry.fetch.service;

import com.aba.industry.model.fuzzysteve.BlueprintData;
import com.aba.industry.model.fuzzysteve.SystemCostIndexes;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FuzzySteveService {
	@GET("/blueprint/api/blueprint.php")
	Call<BlueprintData> getBlueprintData ( @Query("typeid") Long typeId );
	
	@GET("/blueprint/api/costIndexes.php")
	Call<SystemCostIndexes> getSystemCostIndexes ( @Query("solarsystem") String solarSystem );
}
