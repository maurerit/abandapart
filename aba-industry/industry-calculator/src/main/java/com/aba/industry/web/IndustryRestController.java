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

package com.aba.industry.web;

import com.aba.industry.model.BuildCalculationResult;
import com.aba.industry.service.IndustryCalculationService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by maurerit on 7/23/16.
 */
@RestController
@Setter
public class IndustryRestController {
    @Autowired
    private IndustryCalculationService industryCalculationService;

    @RequestMapping( path = "/calculateBuild/{systemName}/{typeId}",
            produces =
                    MediaType
                            .APPLICATION_JSON_VALUE )
    public BuildCalculationResult calculateBuild ( @PathVariable( "systemName" ) String systemName,
                                                   @PathVariable( "typeId" ) Long typeId )
    {
        return industryCalculationService.calculateBuildCosts( systemName, typeId );
    }
}
