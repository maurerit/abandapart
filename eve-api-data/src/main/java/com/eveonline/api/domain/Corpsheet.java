package com.eveonline.api.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "apicorpsheet")
public class Corpsheet
{
    private long corporationId;
    private String corporationName;
    private String ticker;
    private long ceoId;
    private String ceoName;
    private long stationId;
    private String stationName;
    private String description;
    private String url;
    private long allianceId;
    private int taxRate;
    private int memberCount;
    private int memberLimit;
    private int shares;
    private int graphicId;
    private int shape1;
    private int shape2;
    private int shape3;
    private int color1;
    private int color2;
    private int color3;

    @Id
    public long getCorporationId() {
        return corporationId;
    }
    public String getCorporationName() {
        return corporationName;
    }
    public String getTicker() {
        return ticker;
    }
    public long getCeoId() {
        return ceoId;
    }
    public String getCeoName() {
        return ceoName;
    }
    public long getStationId() {
        return stationId;
    }
    public String getStationName() {
        return stationName;
    }
    public String getDescription() {
        return description;
    }
    public String getUrl() {
        return url;
    }
    public long getAllianceId() {
        return allianceId;
    }
    public int getTaxRate() {
        return taxRate;
    }
    public int getMemberCount() {
        return memberCount;
    }
    public int getMemberLimit() {
        return memberLimit;
    }
    public int getShares() {
        return shares;
    }
    public int getGraphicId() {
        return graphicId;
    }
    public int getShape1() {
        return shape1;
    }
    public int getShape2() {
        return shape2;
    }
    public int getShape3() {
        return shape3;
    }
    public int getColor1() {
        return color1;
    }
    public int getColor2() {
        return color2;
    }
    public int getColor3() {
        return color3;
    }

    public void setCorporationId(long corporationId) {
        this.corporationId = corporationId;
    }
    public void setCorporationName(String corporationName) {
        this.corporationName = corporationName;
    }
    public void setTicker(String ticker) {
        this.ticker = ticker;
    }
    public void setCeoId(long ceoId) {
        this.ceoId = ceoId;
    }
    public void setCeoName(String ceoName) {
        this.ceoName = ceoName;
    }
    public void setStationId(long stationId) {
        this.stationId = stationId;
    }
    public void setStationName(String stationName) {
        this.stationName = stationName;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public void setAllianceId(long allianceId) {
        this.allianceId = allianceId;
    }
    public void setTaxRate(int taxRate) {
        this.taxRate = taxRate;
    }
    public void setMemberCount(int memberCount) {
        this.memberCount = memberCount;
    }
    public void setMemberLimit(int memberLimit) {
        this.memberLimit = memberLimit;
    }
    public void setShares(int shares) {
        this.shares = shares;
    }
    public void setGraphicId(int graphicId) {
        this.graphicId = graphicId;
    }
    public void setShape1(int shape1) {
        this.shape1 = shape1;
    }
    public void setShape2(int shape2) {
        this.shape2 = shape2;
    }
    public void setShape3(int shape3) {
        this.shape3 = shape3;
    }
    public void setColor1(int color1) {
        this.color1 = color1;
    }
    public void setColor2(int color2) {
        this.color2 = color2;
    }
    public void setColor3(int color3) {
        this.color3 = color3;
    }
}
