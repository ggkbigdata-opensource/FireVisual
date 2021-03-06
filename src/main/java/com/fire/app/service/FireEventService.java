package com.fire.app.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.fire.app.domain.AppFireEvent;

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
     * @return 
     * @description
     */
    List<JSONObject> getBaseDate(String type, String beginTime);

    /**
     * @createDate 2017年3月29日下午2:50:47 
     * @author wangzhiwang
     * @param id
     * @return 
     * @description 
     */
    List<JSONObject> getAreaDate(Long id);

    /**
     * @createDate 2017年4月17日上午9:35:04 
     * @author wangzhiwang
     * @param streetId
     * @return 
     * @description
     */
    List<AppFireEvent> getStreetEvent(Long streetId);

    /**
     * @createDate 2017年4月17日上午9:56:57 
     * @author wangzhiwang
     * @param id
     * @return 
     * @description
     */
    AppFireEvent getEventById(Long id);

    /**
     * @createDate 2017年4月18日下午3:11:38 
     * @author wangzhiwang
     * @param streetId
     * @param name
     * @param type
     * @return 
     * @description
     */
    List<AppFireEvent> findByStreetIdAndNameAndType(Long streetId, String name, Integer type);

    /**
     * @createDate 2017年4月18日下午4:31:04 
     * @author wangzhiwang
     * @param id
     * @return 
     * @description
     */
    AppFireEvent findEventById(Long id);

    /**
     * @createDate 2017年4月24日上午9:03:57 
     * @author wangzhiwang
     * @param streetId 
     * @param beginTime
     * @param endTime
     * @param type 
     * @return 
     * @description
     */
    List<JSONObject> getBlockData(Long streetId, String beginTime, String endTime, Integer type);

    /**
     * @createDate 2017年4月24日下午2:43:34 
     * @author wangzhiwang
     * @param blockId
     * @param type
     * @param endTime 
     * @param beginTime 
     * @return 
     * @description
     */
    JSONObject getBlockEvent(Long blockId, Integer type, String beginTime, String endTime);

    /**
     * @createDate 2017年4月27日上午8:57:59 
     * @author wangzhiwang
     * @param streetId
     * @param time
     * @param type
     * @return 
     * @description 通过固定月份和类型获取街道对应的数据
     */
    List<JSONObject> getRegionList(Long streetId, String time, Integer type);

    /**
     * @createDate 2017年5月9日上午10:21:52 
     * @author wangzhiwang
     * @param time
     * @param type
     * @return 
     * @description 查看全区里类型不一样的数据
     */
    List<JSONObject> getRegionList(String time, Integer type);

}
