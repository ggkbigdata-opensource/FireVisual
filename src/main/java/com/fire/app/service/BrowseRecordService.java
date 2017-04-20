package com.fire.app.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * @createDate 2017年3月28日下午4:08:47
 * @author wangzhiwang
 * @description
 */
public interface BrowseRecordService {

    /**
     * @createDate 2017年4月19日上午10:13:23 
     * @author wangzhiwang
     * @param id 
     * @return 
     * @description
     */
    List<JSONObject> findByUid(Long id);

    

}
