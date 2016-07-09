package com.aba.industry.fetch;

import com.aba.industry.model.fuzzysteve.BlueprintData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FuzzySteveService {
	@GET("/blueprint/api/blueprint.php")
	Call<BlueprintData> getBlueprintData ( @Query("typeid") Long typeId );
}
