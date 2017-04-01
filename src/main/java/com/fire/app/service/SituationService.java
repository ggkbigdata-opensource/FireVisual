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

}
