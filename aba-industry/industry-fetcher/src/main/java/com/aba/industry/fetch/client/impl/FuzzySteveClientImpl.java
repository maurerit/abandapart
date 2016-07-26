/*
 * Copyright 2016 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.aba.industry.fetch.client.impl;

import com.aba.industry.fetch.client.BuildRequirementsProvider;
import com.aba.industry.fetch.client.CostIndexProvider;
import com.aba.industry.fetch.service.FuzzySteveClient;
import com.aba.industry.model.fuzzysteve.BlueprintData;
import com.aba.industry.model.fuzzysteve.SystemCostIndexes;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;

public class FuzzySteveClientImpl implements BuildRequirementsProvider, CostIndexProvider {
    private static final String fuzzysDomain = "www.fuzzwork.co.uk";
    private FuzzySteveClient client;

    public FuzzySteveClientImpl ( ) {
        OkHttpClient.Builder bob = new OkHttpClient.Builder();
        this.client = new Retrofit.Builder()
                .baseUrl( "https://" + fuzzysDomain )
                .addConverterFactory( JacksonConverterFactory.create() )
                .client( bob.build() )
                .build()
                .create( FuzzySteveClient.class );
    }

    public BlueprintData getBlueprintData ( Long typeId ) throws IOException {
        return this.client.getBlueprintData( typeId )
                          .execute()
                          .body();
    }

    public SystemCostIndexes getSystemCostIndexes ( String systemName ) throws IOException {
        return this.client.getSystemCostIndexes( systemName )
                          .execute()
                          .body();
    }
}
