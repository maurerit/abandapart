package com.aba.eveonline.crest.impl;

import com.aba.eveonline.crest.repo.CrestSolarSystemRepository;
import org.devfleet.crest.retrofit.CrestClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

/**
 * Created by maurerit on 7/27/2016.
 */
@RunWith( MockitoJUnitRunner.class )
public class CrestSolarSystemRepositoryUnitTests {
    @InjectMocks
    CrestSolarSystemRepository repo;

    @Before
    public void setupCrestClient ( ) throws IOException
    {
        repo.setCrestService( CrestClient.TQ()
                                         .build()
                                         .fromDefault() );
    }

    @Test
    public void testGetJitaId ( )
    {
        Long jitaId = repo.getSolarSystemId( "Jita" );

        Assert.assertNotNull( jitaId );
    }

    @Test
    public void testGetUnknownSystemId ( )
    {
        Long whereAreWeId = repo.getSolarSystemId( "Where are we?" );

        Assert.assertNull( whereAreWeId );
    }
}
