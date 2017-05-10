package com.fire.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.fire.app.domain.Street;
import com.fire.app.service.SituationService;
import com.fire.app.service.StreetService;
import com.fire.app.util.ContextHolderUtils;

/**
 * @createDate 2017年3月28日下午3:57:31
 * @author wangzhiwang
 * @description 区域概况
 */
@RequestMapping("/app/situation")
@Controller
public class SituationController {

    @Autowired
    private SituationService situationService;
    @Autowired
    private StreetService streetService;

    @RequestMapping("")
    private String toFireEvent() {
        
        if (!ContextHolderUtils.isLogin()) {
            return "login/login";
        }
        
        return "regionalProfile/regionalProfile";
    }
    
    @RequestMapping(value = "/getAllStreetSituation")
    @ResponseBody
    public List<JSONObject> getAllStreetSituation() {

        List<JSONObject> result = situationService.getAllStreetSituation();

        return result;

    }
    
    @RequestMapping(value = "/event")
    private String toEvent() {
        
        return "regionalProfile/event";
    }
    
    /**
     * @createDate 2017年5月10日下午2:03:57 
     * @author wangzhiwang
     * @return 
     * @description 通过街道，获取街道下对应的所有警情事件---这里警情只是冒烟，火灾
     */
    @RequestMapping(value = "/streetEvent")
    @ResponseBody
    public List<JSONObject> getStreetEvent(@RequestParam(required=true)Long streetId) {

        List<JSONObject> result = situationService.getStreetEvent(streetId);

        return result;

    }
    
    @RequestMapping(value = "/punish")
    private String toPunish() {
        
        return "regionalProfile/punish";
    }
    
    /**
     * @createDate 2017年5月10日下午2:03:57 
     * @author wangzhiwang
     * @return 
     * @description 通过街道，获取街道下对应的所有执法数据
     */
    @RequestMapping(value = "/streetPunish")
    @ResponseBody
    public JSONObject getStreetPunish(@RequestParam(required=true)Long streetId) {

        Street street = streetService.findById(streetId);
        
        JSONObject result = new JSONObject();
        
        List<JSONObject> list = situationService.getStreetPunish(streetId);

        result.put("list", list);
        result.put("street", street.getName());
        result.put("streetId", street.getId());
        return result;

    }
    
    @RequestMapping(value = "/check")
    private String toCheck() {
        
        return "regionalProfile/check";
    }
    
    /**
     * @createDate 2017年5月10日下午2:03:57 
     * @author wangzhiwang
     * @return 
     * @description 通过街道，获取街道下对应的所有检测报告
     */
    @RequestMapping(value = "/streetCheck")
    @ResponseBody
    public List<JSONObject> getAllStreetCheck(String streetId) {

        List<JSONObject> result = situationService.getAllStreetSituation();

        return result;

    }
    
}
