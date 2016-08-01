/*
 * Copyright 2016 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
 * the specific language governing permissions and limitations under the License.
 */

package com.aba.eveonline.repo;

import org.devfleet.crest.model.CrestItem;

import java.util.List;

/**
 * Created by maurerit on 7/25/16.
 */
public interface RegionRepository {
    List<CrestItem> getRegions ( );

    Long findRegionId ( String regionName );
}
