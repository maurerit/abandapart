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

package com.eveonline.api.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table( name = "apicontracts" )
@Data
public class Contracts {
    @Id
    private long       contractId;
    private long       issuerId;
    private long       issuerCorpId;
    private long       assigneeId;
    private long       acceptorId;
    private long       startStationId;
    private long       endStationId;
    private String     type;
    private String     status;
    private String     title;
    private int        forCorp;
    private String     availability;
    private Date       dateIssued;
    private Date       dateExpired;
    private Date       dateAccepted;
    private int        numDays;
    private Date       dateCompleted;
    private BigDecimal price;
    private BigDecimal reward;
    private BigDecimal collateral;
    private BigDecimal buyout;
    private BigDecimal volume;
    private long       corporationId;
}
