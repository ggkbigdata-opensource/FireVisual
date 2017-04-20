package com.fire.app.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

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


}
