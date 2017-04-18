package com.fire.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fire.app.domain.BsBuildingInfo;
import com.fire.app.domain.BsBuildingInfoRepository;
import com.fire.app.service.KeyUnitService;

/**
 * @createDate 2017年3月28日下午4:08:47
 * @author wangzhiwang
 * @description
 */
@Service
public class KeyUnitServiceImpl implements KeyUnitService{

    @Autowired
    private BsBuildingInfoRepository buildingInfoRepository;
    
    
    @Override
    public List<BsBuildingInfo> findUnitByName(String name) {

        List<BsBuildingInfo> result =  buildingInfoRepository.findByPropertyCompanyName(name);
        
        return result;
    }


}
