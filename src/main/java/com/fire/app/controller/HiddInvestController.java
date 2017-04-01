package com.fire.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.fire.app.service.HiddInvestService;
import com.fire.app.util.ContextHolderUtils;

/**
 * @createDate 2017年3月28日下午3:57:31
 * @author wangzhiwang
 * @description
 */
@RequestMapping("/app/hiddInvest")
@Controller
public class HiddInvestController {

    @Autowired
    private HiddInvestService hiddInvestService;

    @RequestMapping("/")
    private String toPage() {
        
        if (!ContextHolderUtils.isLogin()) {
            return "login/login";
        }
        
        return "hiddenInvestigation/hiddenInvestigation";
    }
    
    /**
     * @createDate 2017年3月31日下午5:27:49 
     * @author wangzhiwang
     * @return 
     * @description 进入页面后获取隐患等级数据
     */
    @RequestMapping(value = "/hidVersion")
    @ResponseBody
    public List<JSONObject> hidVersion() {

        List<JSONObject> result = hiddInvestService.getHidVersion();

        return result;

    }
    /**
     * @createDate 2017年3月31日下午5:27:49 
     * @author wangzhiwang
     * @return 
     * @description 隐患排查项目
     */
    @RequestMapping(value = "/investigate")
    @ResponseBody
    public List<JSONObject> investigate() {
        
        List<JSONObject> result = hiddInvestService.getInvestigateItem();
        
        return result;
        
    }
    
}
