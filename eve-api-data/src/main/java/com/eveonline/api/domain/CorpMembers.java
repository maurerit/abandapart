package com.eveonline.api.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "apicorpmembers")
public class CorpMembers
{
    private long characterId;
    private String name;
    private Date startDateTime;
    private long baseId;
    private String base;
    private String title;
    private long corporationId;

    @Id
    public long getCharacterId() {
        return characterId;
    }
    public String getName() {
        return name;
    }
    public Date getStartDateTime() {
        return startDateTime;
    }
    public long getBaseId() {
        return baseId;
    }
    public String getBase() {
        return base;
    }
    public String getTitle() {
        return title;
    }
    public long getCorporationId() {
        return corporationId;
    }

    public void setCharacterId(long characterId) {
        this.characterId = characterId;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }
    public void setBaseId(long baseId) {
        this.baseId = baseId;
    }
    public void setBase(String base) {
        this.base = base;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setCorporationId(long corporationId) {
        this.corporationId = corporationId;
    }
}
