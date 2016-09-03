/*
 * Copyright 2016 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.aba.market;

import lombok.Getter;

/**
 * Created by mm66053 on 8/11/2016.
 */
//TODO: Hard coded Trade Hubs.  Probably not TOO terrible but probably should be configurable or in a db.
@Getter
public enum TradeHubs {
    JITA( "Jita", "The Forge", "Jita IV - Moon 4 - Caldari Navy Assembly Plant", 30000142L, 60003760L ),
    AMARR( "Amarr", "Domain", "Amarr VIII (Oris) - Emperor Family Academy", 30002187L, 60008494L ),
    DODIXIE( "Dodixie", "Sinq Laison", "Dodixie IX - Moon 20 - Federation Navy Assembly Plant", 30002659L, 60011866L ),
    RENS( "Rens", "Heimatar", "Rens VI - Moon 8 - Brutor Tribe Treasury", 30002510L, 60004588L ),
    NULL( "NULL", "NULL", "NULL", 0L, 0L );

    private String stationName;
    private String systemName;
    private String regionName;
    private Long   systemId;
    private Long   stationId;

    TradeHubs ( String systemName, String regionName, String stationName, Long systemId, Long stationId ) {
        this.stationName = stationName;
        this.systemName = systemName;
        this.regionName = regionName;
        this.systemId = systemId;
        this.stationId = stationId;
    }

    public static TradeHubs findBySystemName ( String systemName ) {
        for ( TradeHubs hub : values() ) {
            if ( hub.getSystemName()
                    .equals( systemName ) )
            {
                return hub;
            }
        }
        return NULL;
    }

    public static TradeHubs findBySystemId ( Long systemId ) {
        for ( TradeHubs hub : values() ) {
            if ( hub.getSystemId()
                    .equals( systemId ) )
            {
                return hub;
            }
        }
        return NULL;
    }
}
