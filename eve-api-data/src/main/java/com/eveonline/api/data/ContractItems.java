package com.eveonline.api.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "apicontractitems")
public class ContractItems
{
    private int contractId;
    private int recordId;
    private int typeId;
    private int quantity;
    private int singleton;
    private int included;
    private int corporationId;

    @Id
    public int getRecordId() {
        return recordId;
    }
    public int getContractId() {
        return contractId;
    }
    public int getTypeId() {
        return typeId;
    }
    public int getQuantity() {
        return quantity;
    }
    public int getCorporationId() {
        return corporationId;
    }
    public int getSingleton() {
        return singleton;
    }
    public int getIncluded() {
        return included;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }
    public void setContractId(int contractId) {
        this.contractId = contractId;
    }
    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setCorporationId(int corporationId) {
        this.corporationId = corporationId;
    }
    public void setSingleton(int singleton) {
        this.singleton = singleton;
    }
    public void setIncluded(int included) {
        this.included = included;
    }
}
