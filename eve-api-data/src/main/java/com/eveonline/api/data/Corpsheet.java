package com.eveonline.api.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "apicorpsheet")
public class Corpsheet
{
    private int corporationId;
    private String corporationName;
    private String ticker;
    private int ceoId;
    private String ceoName;
    private int stationId;
    private String stationName;
    private String description;
    private String url;
    private int allianceId;
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
    public int getCorporationId() {
        return corporationId;
    }

    public void setCorporationId(int corporationId) {
        this.corporationId = corporationId;
    }

    public String getCorporationName() {
        return corporationName;
    }

    public void setCorporationName(String corporationName) {
        this.corporationName = corporationName;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public int getCeoId() {
        return ceoId;
    }

    public void setCeoId(int ceoId) {
        this.ceoId = ceoId;
    }

    public String getCeoName() {
        return ceoName;
    }

    public void setCeoName(String ceoName) {
        this.ceoName = ceoName;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getAllianceId() {
        return allianceId;
    }

    public void setAllianceId(int allianceId) {
        this.allianceId = allianceId;
    }

    public int getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(int taxRate) {
        this.taxRate = taxRate;
    }

    public int getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(int memberCount) {
        this.memberCount = memberCount;
    }

    public int getMemberLimit() {
        return memberLimit;
    }

    public void setMemberLimit(int memberLimit) {
        this.memberLimit = memberLimit;
    }

    public int getShares() {
        return shares;
    }

    public void setShares(int shares) {
        this.shares = shares;
    }

    public int getGraphicId() {
        return graphicId;
    }

    public void setGraphicId(int graphicId) {
        this.graphicId = graphicId;
    }

    public int getShape1() {
        return shape1;
    }

    public void setShape1(int shape1) {
        this.shape1 = shape1;
    }

    public int getShape2() {
        return shape2;
    }

    public void setShape2(int shape2) {
        this.shape2 = shape2;
    }

    public int getShape3() {
        return shape3;
    }

    public void setShape3(int shape3) {
        this.shape3 = shape3;
    }

    public int getColor1() {
        return color1;
    }

    public void setColor1(int color1) {
        this.color1 = color1;
    }

    public int getColor2() {
        return color2;
    }

    public void setColor2(int color2) {
        this.color2 = color2;
    }

    public int getColor3() {
        return color3;
    }

    public void setColor3(int color3) {
        this.color3 = color3;
    }
}
