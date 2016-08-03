/*
 * Copyright 2016 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.aba.industry.fetch.service;

import com.aba.industry.fetch.model.FuzzySteveTypeResponse;
import com.aba.industry.model.fuzzysteve.BlueprintData;
import com.aba.industry.model.fuzzysteve.SystemCostIndexes;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FuzzySteveRESTClient {
    @GET( "/blueprint/api/blueprint.php" )
    Call<BlueprintData> getBlueprintData ( @Query( "typeid" ) Long typeId );

    @GET( "/blueprint/api/costIndexes.php" )
    Call<SystemCostIndexes> getSystemCostIndexes ( @Query( "solarsystem" ) String solarSystem );

    @GET( "/api/typeid.php" )
    Call<FuzzySteveTypeResponse> getTypeIdForTypeName ( @Query( "typename" ) String typeName );
}
