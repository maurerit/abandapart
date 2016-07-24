/*
 * Copyright 2016 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.eveonline.api.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "apicontractitems" )
public class ContractItems {
    private long contractId;
    private long recordId;
    private int  typeId;
    private int  quantity;
    private int  singleton;
    private int  included;
    private long corporationId;

    @Id
    public long getRecordId ( ) {
        return recordId;
    }

    public void setRecordId ( long recordId ) {
        this.recordId = recordId;
    }

    public long getContractId ( ) {
        return contractId;
    }

    public void setContractId ( long contractId ) {
        this.contractId = contractId;
    }

    public int getTypeId ( ) {
        return typeId;
    }

    public void setTypeId ( int typeId ) {
        this.typeId = typeId;
    }

    public int getQuantity ( ) {
        return quantity;
    }

    public void setQuantity ( int quantity ) {
        this.quantity = quantity;
    }

    public long getCorporationId ( ) {
        return corporationId;
    }

    public void setCorporationId ( long corporationId ) {
        this.corporationId = corporationId;
    }

    public int getSingleton ( ) {
        return singleton;
    }

    public void setSingleton ( int singleton ) {
        this.singleton = singleton;
    }

    public int getIncluded ( ) {
        return included;
    }

    public void setIncluded ( int included ) {
        this.included = included;
    }
}
