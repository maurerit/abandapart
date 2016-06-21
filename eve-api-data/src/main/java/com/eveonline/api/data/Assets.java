package com.eveonline.api.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "apiassets")
public class Assets
{
    private long itemId;
    private long parentItemId;
    private long locationId;
    private int typeId;
    private int quantity;
    private int flag;
    private int singleton;
    private int rawQuantity;
    private long corporationId;

    @Id
    public long getItemId() {
        return itemId;
    }
    public long getParentItemId() {
        return parentItemId;
    }
    public long getLocationId() {
        return locationId;
    }
    public int getTypeId() {
        return typeId;
    }
    public int getQuantity() {
        return quantity;
    }
    public int getFlag() {
        return flag;
    }
    public long getCorporationId() {
        return corporationId;
    }
    public int getSingleton() {
        return singleton;
    }
    public int getRawQuantity() {
        return rawQuantity;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }
    public void setParentItemId(long parentItemId) {
        this.parentItemId = parentItemId;
    }
    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }
    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setFlag(int flag) {
        this.flag = flag;
    }
    public void setCorporationId(long corporationId) {
        this.corporationId = corporationId;
    }
    public void setSingleton(int singleton) {
        this.singleton = singleton;
    }
    public void setRawQuantity(int rawQuantity) {
        this.rawQuantity = rawQuantity;
    }
}
