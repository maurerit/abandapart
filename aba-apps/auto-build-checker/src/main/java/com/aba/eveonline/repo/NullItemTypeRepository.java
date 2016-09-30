/*
 * Copyright 2016 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.aba.eveonline.repo;

import org.devfleet.crest.model.CrestType;

/**
 * Created by maurerit on 9/7/16.
 */
public class NullItemTypeRepository implements ItemTypeRepository {
    private static final CrestType NULL_TYPE = new CrestType();

    static {
        NULL_TYPE.setName( "NULL" );
        NULL_TYPE.setDescription( "NULL" );
        NULL_TYPE.setHref( "NULL" );
        NULL_TYPE.setId( 0 );
    }

    @Override
    public CrestType getItemDetails ( int itemId ) {
        return NULL_TYPE;
    }
}
