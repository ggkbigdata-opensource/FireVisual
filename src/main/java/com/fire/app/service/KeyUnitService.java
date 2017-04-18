package com.fire.app.service;

import java.util.List;

import com.fire.app.domain.BsBuildingInfo;

/**
 * @createDate 2017年3月28日下午4:08:47
 * @author wangzhiwang
 * @description
 */
public interface KeyUnitService {

    /**
     * @createDate 2017年4月18日上午10:27:21 
     * @author wangzhiwang
     * @param name
     * @return 
     * @description
     */
    List<BsBuildingInfo> findUnitByName(String name);


}
