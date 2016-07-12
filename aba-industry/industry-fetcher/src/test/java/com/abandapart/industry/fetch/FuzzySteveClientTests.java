package com.abandapart.industry.fetch;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import com.aba.industry.fetch.client.FuzzySteveClientImpl;
import com.aba.industry.model.fuzzysteve.ActivityMaterial;
import com.aba.industry.model.fuzzysteve.BlueprintData;

public class FuzzySteveClientTests {

	private FuzzySteveClientImpl client = new FuzzySteveClientImpl();
	
	@Test
	public void testGetSleipnirBlueprintDetails() throws IOException {
		BlueprintData bpData = client.getBlueprintData(22444l);
		
		assertNotNull("Blueprint Details should not be null", bpData);
		assertNotNull(bpData.getActivityMaterials());
		assertNotNull(bpData.getBlueprintDetails());
		assertNotNull(bpData.getBlueprintSkills());
		assertNotNull(bpData.getDecryptors());
		assertNotNull(bpData.getRequestedId());
		assertTrue(bpData.getRequestedId() > 0);
		
		for ( ActivityMaterial material : bpData.getDecryptors() ) {
			assertNotNull("Decryptor name should not be null", material.getName());
			assertNotNull("Decryptor typeId should not be null", material.getTypeId());
		}
	}

}
