package com.eveonline.api.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "apicorpmembers")
public class CorpMembers
{
    private int characterId;
    private String name;
    private Date startDateTime;
    private int baseId;
    private String base;
    private String title;
    private int corporationId;

    @Id
    public int getCharacterId() {
        return characterId;
    }
    public String getName() {
        return name;
    }
    public Date getStartDateTime() {
        return startDateTime;
    }
    public int getBaseId() {
        return baseId;
    }
    public String getBase() {
        return base;
    }
    public String getTitle() {
        return title;
    }
    public int getCorporationId() {
        return corporationId;
    }

    public void setCharacterId(int characterId) {
        this.characterId = characterId;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }
    public void setBaseId(int baseId) {
        this.baseId = baseId;
    }
    public void setBase(String base) {
        this.base = base;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setCorporationId(int corporationId) {
        this.corporationId = corporationId;
    }
}
