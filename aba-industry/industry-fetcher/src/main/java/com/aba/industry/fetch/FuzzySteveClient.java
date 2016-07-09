package com.aba.industry.fetch;

import java.io.IOException;

import com.aba.industry.model.fuzzysteve.BlueprintData;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class FuzzySteveClient {
	private static final String fuzzysDomain = "www.fuzzwork.co.uk";
	private FuzzySteveService client;
	
	public FuzzySteveClient ( ) {
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
}
