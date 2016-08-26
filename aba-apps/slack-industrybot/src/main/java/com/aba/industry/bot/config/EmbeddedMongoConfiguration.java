/*
 * Copyright 2016 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.aba.industry.bot.config;

import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.config.Storage;
import de.flapdoodle.embed.mongo.distribution.Feature;
import de.flapdoodle.embed.mongo.distribution.IFeatureAwareVersion;
import de.flapdoodle.embed.process.runtime.Network;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Set;

/**
 * Created by maurerit on 8/23/16.
 */
@Configuration
@EnableConfigurationProperties( { MongoProperties.class, EmbeddedMongoProperties.class } )
public class EmbeddedMongoConfiguration {
    private static final byte[] IP4_LOOPBACK_ADDRESS = { 127, 0, 0, 1 };

    private static final byte[] IP6_LOOPBACK_ADDRESS = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 1 };

    @Autowired
    private MongoProperties properties;

    @Autowired
    private EmbeddedMongoProperties embeddedProperties;

    @Bean
    public IMongodConfig customMongoConfig ( ) throws IOException {
        IFeatureAwareVersion featureAwareVersion = new ToStringFriendlyFeatureAwareVersion(
                this.embeddedProperties.getVersion(),
                this.embeddedProperties.getFeatures() );
        MongodConfigBuilder builder = new MongodConfigBuilder()
                .version( featureAwareVersion );
        if ( getPort() > 0 ) {
            builder.net( new Net( getHost().getHostAddress(), getPort(),
                                  Network.localhostIsIPv6() ) );
        }
        else {
            builder.net( new Net( getHost().getHostAddress(),
                                  Network.getFreeServerPort( getHost() ), Network.localhostIsIPv6() ) );
        }
        //TODO: Make this a configuration property.
        Storage storage = new Storage( Paths.get( System.getProperty( "user.dir" ), "mongodb" )
                                            .toString(), null, 0 );

        if ( Files.notExists( Paths.get( storage.getDatabaseDir() ) ) ) {
            Files.createDirectories( Paths.get( storage.getDatabaseDir() ) );
        }

        builder.replication( storage );
        return builder.build();
    }

    private int getPort ( ) {
        if ( this.properties.getPort() == null ) {
            return MongoProperties.DEFAULT_PORT;
        }
        return this.properties.getPort();
    }

    private InetAddress getHost ( ) throws UnknownHostException {
        if ( this.properties.getHost() == null ) {
            return InetAddress.getByAddress( Network.localhostIsIPv6()
                                                     ? IP6_LOOPBACK_ADDRESS : IP4_LOOPBACK_ADDRESS );
        }
        return InetAddress.getByName( this.properties.getHost() );
    }

    /**
     * A workaround for the lack of a {@code toString} implementation on
     * {@code GenericFeatureAwareVersion}.
     */
    private final static class ToStringFriendlyFeatureAwareVersion
            implements IFeatureAwareVersion
    {

        private final String version;

        private final Set<Feature> features;

        private ToStringFriendlyFeatureAwareVersion ( String version,
                                                      Set<Feature> features )
        {
            Assert.notNull( version, "version must not be null" );
            this.version = version;
            this.features = ( features == null ? Collections.<Feature>emptySet()
                    : features );
        }

        @Override
        public String asInDownloadPath ( ) {
            return this.version;
        }

        @Override
        public boolean enabled ( Feature feature ) {
            return this.features.contains( feature );
        }

        @Override
        public int hashCode ( ) {
            final int prime = 31;
            int result = 1;
            result = prime * result + this.features.hashCode();
            result = prime * result + this.version.hashCode();
            return result;
        }

        @Override
        public boolean equals ( Object obj ) {
            if ( this == obj ) {
                return true;
            }
            if ( obj == null ) {
                return false;
            }
            if ( getClass() == obj.getClass() ) {
                ToStringFriendlyFeatureAwareVersion other = (ToStringFriendlyFeatureAwareVersion) obj;
                boolean equals = true;
                equals &= this.features.equals( other.features );
                equals &= this.version.equals( other.version );
                return equals;
            }
            return super.equals( obj );
        }

        @Override
        public String toString ( ) {
            return this.version;
        }

    }
}
