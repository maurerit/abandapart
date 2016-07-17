package com.aba.industry.fetch.client;

import java.io.IOException;

import com.aba.industry.fetch.service.FuzzySteveService;
import com.aba.industry.model.fuzzysteve.BlueprintData;
import com.aba.industry.model.fuzzysteve.SystemCostIndexes;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class FuzzySteveClientImpl implements BuildRequirementsProvider, CostIndexProvider {
	private static final String fuzzysDomain = "www.fuzzwork.co.uk";
	private FuzzySteveService client;
	
	public FuzzySteveClientImpl ( ) {
		OkHttpClient.Builder bob = new OkHttpClient.Builder();
		this.client = new Retrofit.Builder()
						  .baseUrl("https://" + fuzzysDomain)
						  .addConverterFactory(JacksonConverterFactory.create())
						  .client(bob.build())
						  .build()
						  .create(FuzzySteveService.class);
	}
	
	public BlueprintData getBlueprintData ( Long typeId ) throws IOException {
		return this.client.getBlueprintData(typeId).execute().body();
	}
	
	public SystemCostIndexes getSystemCostIndexes ( String systemName ) throws IOException {
		return this.client.getSystemCostIndexes(systemName).execute().body();
	}
}
