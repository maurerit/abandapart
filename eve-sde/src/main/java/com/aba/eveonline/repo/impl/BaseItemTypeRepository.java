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
package com.aba.eveonline.repo.impl;

import com.aba.eveonline.model.Type;
import com.aba.eveonline.repo.ItemTypeRepository;
import org.devfleet.crest.model.CrestType;

/**
 * This class only exists to bridge the gap between SDE lookups and CREST lookups.
 * I decided to expand the {@link ItemTypeRepository} interface to have it define methods
 * for both style of lookups and didn't want no-imple methods littered all over the place.
 * Even though this framework is still quite young it will already be going through some
 * fairly drastic changes since CCP decided to eventually drop CREST and replace it with
 * ESI.  This being the case, anything specifically returning or interacting with CREST
 * through the eve-crest-java API will eventually be abstracted so that they don't return
 * any datatype specifically implemented by any third party API.
 */
public abstract class BaseItemTypeRepository implements ItemTypeRepository {
    @Override
    public CrestType fetchItemDetails(long typeId) {
        return null;
    }

    @Override
    public Type findByTypeId(long typeId) {
        return null;
    }

    @Override
    public Type findByName(String name) {
        return null;
    }
}
