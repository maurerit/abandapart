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

import com.aba.industry.fetch.client.CostIndexProvider;
import com.aba.industry.fetch.client.TypeNameToTypeIdProvider;
import com.aba.industry.fetch.model.FuzzySteveTypeResponse;
import com.aba.industry.fetch.service.FuzzySteveRESTClient;
import com.aba.industry.model.fuzzysteve.BlueprintData;
import com.aba.industry.model.fuzzysteve.SystemCostIndexes;
import okhttp3.OkHttpClient;
import org.springframework.cache.annotation.Cacheable;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;

public class FuzzySteveService implements /*BuildRequirementsProvider,*/ CostIndexProvider, TypeNameToTypeIdProvider {
    private static final String fuzzysDomain = "www.fuzzwork.co.uk";
    private FuzzySteveRESTClient client;

    public FuzzySteveService ( )
    {
        OkHttpClient.Builder bob = new OkHttpClient.Builder();
        this.client = new Retrofit.Builder()
                .baseUrl( "https://" + fuzzysDomain )
                .addConverterFactory( JacksonConverterFactory.create() )
                .client( bob.build() )
                .build()
                .create( FuzzySteveRESTClient.class );
    }

    //    @Override
    public BlueprintData getBlueprintData ( Long typeId ) throws IOException {
        return this.client.getBlueprintData( typeId )
                          .execute()
                          .body();
    }

    @Override
    public SystemCostIndexes getSystemCostIndexes ( String systemName ) throws IOException {
        return this.client.getSystemCostIndexes( systemName )
                          .execute()
                          .body();
    }

    @Override
    @Cacheable( "typename-to-typeid" )
    public Integer getTypeIdForTypeName ( String typeName ) throws IOException {
        Integer result = -1;
        FuzzySteveTypeResponse response = this.client.getTypeIdForTypeName( typeName )
                                                     .execute()
                                                     .body();

        if ( !"bad item".equals( response.getTypeName() ) ) {
            result = response.getTypeID();
        }

        return result;
    }
}
