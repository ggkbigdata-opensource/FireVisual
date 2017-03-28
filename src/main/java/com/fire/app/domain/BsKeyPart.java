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
public class BsKeyPart {

    @Id
    @GeneratedValue
    private Long id;
    private String itemNumber;

    private String keyPartName;
    private String floor;
    private String position;
    private String area;
    private String fireEquipment;
    private String dutyNum;
    private String diplomaNum;
    private String firePumpNum;
    private String sprayPumpNum;
    private String pressurePumpNum;
    private String airTankVolume;
    private String storageArea;
    private String oilVolume;

    private String remark;
    private String creator;
    private String createTime;

    public BsKeyPart(){
        
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

    public String getKeyPartName() {
        return keyPartName;
    }

    public void setKeyPartName(String keyPartName) {
        this.keyPartName = keyPartName;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getFireEquipment() {
        return fireEquipment;
    }

    public void setFireEquipment(String fireEquipment) {
        this.fireEquipment = fireEquipment;
    }

    public String getDutyNum() {
        return dutyNum;
    }

    public void setDutyNum(String dutyNum) {
        this.dutyNum = dutyNum;
    }

    public String getDiplomaNum() {
        return diplomaNum;
    }

    public void setDiplomaNum(String diplomaNum) {
        this.diplomaNum = diplomaNum;
    }

    public String getFirePumpNum() {
        return firePumpNum;
    }

    public void setFirePumpNum(String firePumpNum) {
        this.firePumpNum = firePumpNum;
    }

    public String getSprayPumpNum() {
        return sprayPumpNum;
    }

    public void setSprayPumpNum(String sprayPumpNum) {
        this.sprayPumpNum = sprayPumpNum;
    }

    public String getPressurePumpNum() {
        return pressurePumpNum;
    }

    public void setPressurePumpNum(String pressurePumpNum) {
        this.pressurePumpNum = pressurePumpNum;
    }

    public String getAirTankVolume() {
        return airTankVolume;
    }

    public void setAirTankVolume(String airTankVolume) {
        this.airTankVolume = airTankVolume;
    }

    public String getStorageArea() {
        return storageArea;
    }

    public void setStorageArea(String storageArea) {
        this.storageArea = storageArea;
    }

    public String getOilVolume() {
        return oilVolume;
    }

    public void setOilVolume(String oilVolume) {
        this.oilVolume = oilVolume;
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
