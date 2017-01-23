/*
 * Copyright 2016 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.aba.industry.model;

import com.aba.data.domain.config.BuildOrBuyConfiguration;
import com.aba.data.domain.config.IndustrySkillConfiguration;
import com.aba.data.domain.config.InventionSkillConfiguration;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by maurerit on 8/6/16.
 */
@Data
public class BuildCalculationRequest {
    public static final String topic = "BUILD_CALCULATION_REQUEST";
    @JsonProperty
    private String                        systemName;
    @JsonProperty
    private Integer                       requestedBuildTypeId;
    @JsonProperty
    private String                        requestedBuildTypeName;
    @JsonProperty
    private IndustrySkillConfiguration    industrySkills;
    @JsonProperty
    private InventionSkillConfiguration   inventionSkills;
    @JsonProperty
    private Integer                       meLevel;
    @JsonProperty
    private Integer                       teLevel;
    @JsonProperty
    private Decryptor                     decryptor;
    @JsonProperty
    private boolean                       findPrices;
    @JsonProperty
    private boolean                       useBuildOrBuyConfigurations;
    @JsonProperty
    private List<BuildOrBuyConfiguration> buildOrBuyConfigurationList;
    @JsonProperty
    private Boolean suppressSalary       = false;
    @JsonProperty
    private Boolean suppressFreight      = false;
    @JsonProperty
    private Boolean suppressInstallation = false;
    @JsonProperty
    private Long entityId;

    public BuildCalculationRequest copy ( ) {
        BuildCalculationRequest result = new BuildCalculationRequest();

        result.setSystemName( systemName );
        result.setRequestedBuildTypeId( requestedBuildTypeId );
        result.setRequestedBuildTypeName( requestedBuildTypeName );
        result.setIndustrySkills( industrySkills );
        result.setInventionSkills( inventionSkills );
        result.setMeLevel( meLevel );
        result.setTeLevel( teLevel );
        result.setDecryptor( decryptor );
        result.setFindPrices( findPrices );
        result.setUseBuildOrBuyConfigurations( useBuildOrBuyConfigurations );
        result.setBuildOrBuyConfigurationList( buildOrBuyConfigurationList );
        result.setSuppressSalary( suppressSalary );
        result.setSuppressFreight( suppressFreight );
        result.setSuppressInstallation( suppressInstallation );
        result.setEntityId(entityId);

        return result;
    }
}
