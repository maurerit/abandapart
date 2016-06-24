package com.eveonline.api.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "apicontractitems")
public class ContractItems
{
    private long contractId;
    private long recordId;
    private int typeId;
    private int quantity;
    private int singleton;
    private int included;
    private long corporationId;

    @Id
    public long getRecordId() {
        return recordId;
    }
    public long getContractId() {
        return contractId;
    }
    public int getTypeId() {
        return typeId;
    }
    public int getQuantity() {
        return quantity;
    }
    public long getCorporationId() {
        return corporationId;
    }
    public int getSingleton() {
        return singleton;
    }
    public int getIncluded() {
        return included;
    }

    public void setRecordId(long recordId) {
        this.recordId = recordId;
    }
    public void setContractId(long contractId) {
        this.contractId = contractId;
    }
    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setCorporationId(long corporationId) {
        this.corporationId = corporationId;
    }
    public void setSingleton(int singleton) {
        this.singleton = singleton;
    }
    public void setIncluded(int included) {
        this.included = included;
    }
}
