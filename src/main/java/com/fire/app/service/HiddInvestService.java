package com.fire.app.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.fire.app.domain.BsBuildingInfo;
import com.fire.app.domain.CrCheckReportInfo;

/**
 * @createDate 2017年3月31日下午5:31:12
 * @author wangzhiwang
 * @description
 */
public interface HiddInvestService {

    /**
     * @createDate 2017年3月31日下午5:32:56 
     * @author wangzhiwang
     * @return 
     * @description 获取隐患等级
     */
    List<JSONObject> getHidGrade();

    /**
     * @createDate 2017年3月31日下午5:33:18 
     * @author wangzhiwang
     * @return 
     * @description 获取隐患排查项目
     */
    List<JSONObject> getInvestigateItem();

    /**
     * @createDate 2017年4月1日下午2:55:52 
     * @author wangzhiwang
     * @param streetId
     * @param name 
     * @param itemName 
     * @return 
     * @description
     */
    List<JSONObject> getDetailDate(Long streetId, String name);

    /**
     * @createDate 2017年5月10日下午4:12:04 
     * @author wangzhiwang
     * @param string
     * @return 
     * @description 通过项目编号获取建筑概况表数据
     */
    BsBuildingInfo findBuildingInfoByRepurtNum(String string);

    /**
     * @createDate 2017年5月10日下午5:58:42 
     * @author wangzhiwang
     * @param string
     * @return 
     * @description
     */
    CrCheckReportInfo findCheckInfoByRepurtNum(String string);

}
