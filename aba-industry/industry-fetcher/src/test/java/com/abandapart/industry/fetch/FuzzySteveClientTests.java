package com.abandapart.industry.fetch;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import com.aba.industry.fetch.FuzzySteveClient;
import com.aba.industry.model.fuzzysteve.BlueprintData;

public class FuzzySteveClientTests {

	private FuzzySteveClient client = new FuzzySteveClient();
	
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
	}

}
