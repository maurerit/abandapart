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

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table( name = "apiwalletjournal" )
@IdClass( WalletJournalId.class )
@Data
public class WalletJournal {
    @Column( name = "date" )
    @Id
    private Date       entryDate;
    @Id
    private long       refId;
    private int        refTypeId;
    private String     ownerNameOne;
    private int        ownerIdOne;
    private String     ownerNameTwo;
    private int        ownerIdTwo;
    private String     argNameOne;
    private int        argIdOne;
    private BigDecimal amount;
    private BigDecimal balance;
    private String     reason;
    private int        accountKey;
    private long       corporationId;
}
