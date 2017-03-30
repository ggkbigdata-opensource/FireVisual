package com.fire.app.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * @createDate 2017年3月28日下午4:08:47
 * @author wangzhiwang
 * @description
 */
public interface PunishmentService {

    /**
     * @createDate 2017年3月30日下午2:02:22 
     * @author wangzhiwang
     * @return 
     * @description
     */
    List<JSONObject> getData();

    /**
     * @createDate 2017年3月30日下午2:04:58 
     * @author wangzhiwang
     * @param id
     * @return 
     * @description
     */
    List<JSONObject> getAreaDate(Long id);

    /**
     * @createDate 2017年3月30日下午2:05:04 
     * @author wangzhiwang
     * @param beginTime
     * @param endTime
     * @return 
     * @description
     */
    List<JSONObject> getBaseDate(String beginTime, String endTime);


}
