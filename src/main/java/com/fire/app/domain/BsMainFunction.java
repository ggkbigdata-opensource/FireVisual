package com.fire.app.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @createDate 2017年3月21日下午5:53:16
 * @author wangzhiwang
 * @description
 */
@Entity
public class BsMainFunction {

    @Id
    @GeneratedValue
    private Long id;
    private String itemNumber;
    private String buildingFloor;
    private String function;
    private String isSpecificLocation;
    private String funBusinessName;
    private String funBuildArea;
    
    private String remark;
    private String creator;
    private String createTime;
    
    public BsMainFunction(){
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getBuildingFloor() {
        return buildingFloor;
    }

    public void setBuildingFloor(String buildingFloor) {
        this.buildingFloor = buildingFloor;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getIsSpecificLocation() {
        return isSpecificLocation;
    }

    public void setIsSpecificLocation(String isSpecificLocation) {
        this.isSpecificLocation = isSpecificLocation;
    }

    public String getFunBusinessName() {
        return funBusinessName;
    }

    public void setFunBusinessName(String funBusinessName) {
        this.funBusinessName = funBusinessName;
    }

    public String getFunBuildArea() {
        return funBuildArea;
    }

    public void setFunBuildArea(String funBuildArea) {
        this.funBuildArea = funBuildArea;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    
    
}
