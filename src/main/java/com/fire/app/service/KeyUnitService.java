package com.fire.app.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.fire.app.domain.BsBuildingInfo;

/**
 * @createDate 2017年3月28日下午4:08:47
 * @author wangzhiwang
 * @description
 */
public interface KeyUnitService {


    /**
     * @createDate 2017年4月20日上午11:15:30 
     * @author wangzhiwang
     * @param uid
     * @return 
     * @description
     */
    List<JSONObject> findByUid(Long uid);

    /**
     * @createDate 2017年4月20日上午11:16:19 
     * @author wangzhiwang
     * @param name
     * @return 
     * @description
     */
    List<JSONObject> findUnitByName(String name);

    /**
     * @createDate 2017年5月9日下午5:51:10 
     * @author wangzhiwang
     * @param id
     * @return 
     * @description 通过id获取建筑主体详情
     */
    BsBuildingInfo findById(Long id);

    /**
     * @createDate 2017年5月9日下午10:18:16 
     * @author wangzhiwang
     * @param id
     * @return 
     * @description 通过建筑主体ID获取数据
     */
    BsBuildingInfo findBuildingInfoById(Long id);


}
