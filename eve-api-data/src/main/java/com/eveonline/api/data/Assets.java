package com.eveonline.api.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "apiassets")
public class Assets
{
    private int itemId;
    private int parentItemId;
    private int locationId;
    private int typeId;
    private int quantity;
    private int flag;
    private int singleton;
    private int rawQuantity;
    private int corporationId;

    @Id
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getParentItemId() {
        return parentItemId;
    }

    public void setParentItemId(int parentItemId) {
        this.parentItemId = parentItemId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getCorporationId() {
        return corporationId;
    }

    public void setCorporationId(int corporationId) {
        this.corporationId = corporationId;
    }

    public int getSingleton() {
        return singleton;
    }

    public void setSingleton(int singleton) {
        this.singleton = singleton;
    }

    public int getRawQuantity() {
        return rawQuantity;
    }

    public void setRawQuantity(int rawQuantity) {
        this.rawQuantity = rawQuantity;
    }
}
