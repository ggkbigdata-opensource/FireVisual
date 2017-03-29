package com.fire.app.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * @createDate 2017年3月28日下午4:08:47
 * @author wangzhiwang
 * @description
 */
public interface FireEventService {

    /**
     * @createDate 2017年3月28日下午4:46:58 
     * @author wangzhiwang
     * @return 
     * @description
     */
    List<JSONObject> getData();

    /**
     * @createDate 2017年3月29日上午10:15:36 
     * @author wangzhiwang
     * @param type
     * @param beginTime
     * @param endTime
     * @return 
     * @description
     */
    List<JSONObject> getBaseDate(String type, String beginTime, String endTime);

}
