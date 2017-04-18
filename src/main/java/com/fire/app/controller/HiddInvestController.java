package com.fire.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.fire.app.service.HiddInvestService;
import com.fire.app.util.ContextHolderUtils;

import ch.qos.logback.core.rolling.helper.IntegerTokenConverter;

/**
 * @createDate 2017年3月28日下午3:57:31
 * @author wangzhiwang
 * @description
 */
@RequestMapping(value = "/app/hiddInvest")
@Controller
public class HiddInvestController {

    @Autowired
    private HiddInvestService hiddInvestService;

    @RequestMapping(value="")
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
    @RequestMapping(value = "/hidGrade")
    @ResponseBody
    public List<JSONObject> hidGrade() {

        List<JSONObject> result = hiddInvestService.getHidGrade();

        return result;

    }
    
    @RequestMapping(value = "/detail")
    private String toDetail(HttpServletRequest request,Long streetId,String name) {
        
        if (!ContextHolderUtils.isLogin()) {
            return "login/login";
        }
        
        List<JSONObject> result = hiddInvestService.getDetailDate(streetId,name);
        
        request.setAttribute("result", result);
        
        return "hiddenInvestigation/securityDetail";
    }
    /**
     * @createDate 2017年4月1日下午2:48:42 
     * @author wangzhiwang
     * @param streetId
     * @return 
     * @description  获取街道里面的详细数据
     */
    @RequestMapping(value = "/getDetail")
    @ResponseBody
    public List<JSONObject> hidGradeStreetDetail(Long streetId,String name) {
        
        List<JSONObject> result = hiddInvestService.getDetailDate(streetId,name);
        
        return result;
        
    }
    
    
    @RequestMapping(value = "/toBuidDetail")
    private String toBuidDetailPage() {
        
        if (!ContextHolderUtils.isLogin()) {
            return "login/login";
        }
        
        return "hiddenInvestigation/detail-menu";
    }
    
    /**
     * @createDate 2017年4月18日上午9:13:38 
     * @author wangzhiwang
     * @param number
     * @return 
     * @description 点击页面上五个点，返回对应的数据
     */
    @RequestMapping(value = "/getBuidDetail")
    private void getBuidDetail(@RequestParam(required=true) Integer count) {
        //1--建筑概况表     2--建筑主体使用功能表     3--物业管理单位概况表     4--消防重点部位概况表     5--主要消防系统概况表
        
        
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
