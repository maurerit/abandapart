/*
 * Copyright 2016 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.aba.data.domain.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

/**
 * Created by maurerit on 10/16/16.
 */
@Data
public class ApiKeyInfo {
    private Long   accessMask;
    @JsonProperty( "created_at" )
    @JsonFormat( pattern = "yyyy-MM-dd hh:mm:ss" )
    private Date   createdAt;
    @JsonFormat( pattern = "yyyy-MM-dd hh:mm:ss" )
    private Date   expires;
    @JsonProperty( "keyID" )
    private Long   keyId;
    // TODO: Make this an enum but I can't be assed right now to remember the possible values
    private String type;
    @JsonProperty( "updated_at" )
    @JsonFormat( pattern = "yyyy-MM-dd hh:mm:ss" )
    private Date   updatedAt;
}
