package com.fire.app.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.fire.app.domain.AppPunishment;

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

    /**
     * @createDate 2017年4月18日下午5:17:26 
     * @author wangzhiwang
     * @param streetId
     * @param name
     * @param type
     * @return 
     * @description
     */
    List<AppPunishment> findByStreetIdAndNameAndType(Long streetId, String name, Integer type);

    /**
     * @createDate 2017年4月19日上午9:10:31 
     * @author wangzhiwang
     * @param id
     * @return 
     * @description
     */
    AppPunishment fingById(Long id);


    List<JSONObject> getBlockData(Long streetId, String beginTime, String endTime);
}
