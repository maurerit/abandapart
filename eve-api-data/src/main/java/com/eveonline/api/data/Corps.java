package com.eveonline.api.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "apicorps")
public class Corps
{
    private int corporationID;
    private String corporationName;
    private int characterId;
    private String characterName;
    private String keyId;

    @Id
    public int getCorporationID() {
        return corporationID;
    }
    public String getCorporationName() {
        return corporationName;
    }
    public int getCharacterId() {
        return characterId;
    }
    public String getCharacterName() {
        return characterName;
    }
    public String getKeyId() {
        return keyId;
    }

    public void setCorporationID(int corporationID) {
        this.corporationID = corporationID;
    }
    public void setCorporationName(String corporationName) {
        this.corporationName = corporationName;
    }
    public void setCharacterId(int characterId) {
        this.characterId = characterId;
    }
    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }
    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }
}
