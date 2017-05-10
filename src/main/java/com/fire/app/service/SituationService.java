package com.fire.app.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * @createDate 2017年3月31日上午10:40:11
 * @author wangzhiwang
 * @description
 */
public interface SituationService {

    /**
     * @createDate 2017年3月31日上午10:41:17 
     * @author wangzhiwang
     * @return 
     * @description 获取每个街道的概况
     */
    List<JSONObject> getAllStreetSituation();

    /**
     * @createDate 2017年5月10日下午3:18:29 
     * @author wangzhiwang
     * @param streetId
     * @return 
     * @description 通过街道id获取街道下的所有火灾冒烟数据
     */
    List<JSONObject> getStreetEvent(Long streetId);

    /**
     * @createDate 2017年5月10日下午3:27:55 
     * @author wangzhiwang
     * @param streetId
     * @return 
     * @description 通过街道id获取街道下的所有执法
     */
    List<JSONObject> getStreetPunish(Long streetId);

}
