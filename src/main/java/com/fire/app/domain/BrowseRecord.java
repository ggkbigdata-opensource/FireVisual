package com.fire.app.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class BrowseRecord  {
    // Fields
    @Id
    @GeneratedValue
    private Long id;
    private Long uid;
    private Long buildingInfoId;
    private Date browseT;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getUid() {
        return uid;
    }
    public void setUid(Long uid) {
        this.uid = uid;
    }
    public Long getBuildingInfoId() {
        return buildingInfoId;
    }
    public void setBuildingInfoId(Long buildingInfoId) {
        this.buildingInfoId = buildingInfoId;
    }
    public Date getBrowseT() {
        return browseT;
    }
    public void setBrowseT(Date browseT) {
        this.browseT = browseT;
    }
    
}
