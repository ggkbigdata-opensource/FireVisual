package com.fire.app.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

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
    List<JSONObject> getHidVersion();

    /**
     * @createDate 2017年3月31日下午5:33:18 
     * @author wangzhiwang
     * @return 
     * @description 获取隐患排查项目
     */
    List<JSONObject> getInvestigateItem();

}
