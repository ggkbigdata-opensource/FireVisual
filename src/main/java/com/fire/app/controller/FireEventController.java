package com.fire.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.fire.app.domain.AppFireEvent;
import com.fire.app.service.FireEventService;
import com.fire.app.util.ContextHolderUtils;

/**
 * @createDate 2017年3月28日下午3:57:31
 * @author wangzhiwang
 * @description
 */
@RequestMapping(value = "/app/fireEvent")
@Controller
public class FireEventController {

    @Autowired
    private FireEventService fireEventServcie;

    @RequestMapping("/")
    private String toFireEvent() {
        
        if (!ContextHolderUtils.isLogin()) {
            return "login/login";
        }
        
        return "alarm/alarm-tianHe";
    }
    
    @RequestMapping(value = "/main")
    @ResponseBody
    public List<JSONObject> main() {

        List<JSONObject> result = fireEventServcie.getData();

        return result;

    }
    
    @RequestMapping("/toBasePage")
    private String toBasePage() {
        
      /*  if (!ContextHolderUtils.isLogin()) {
            return "login/login";
        }*/
        
        return "alarm/alarm-fire";
    }
    
    /**
     * @createDate 2017年3月29日上午10:14:53 
     * @author wangzhiwang
     * @param
     * @param beginTime
     * @param endTime
     * @return 
     * @description 获取每个街道详情
     */
    @RequestMapping(value = "/getBaseDate",method = RequestMethod.POST)
    @ResponseBody
    private List<JSONObject> getBaseDate(String beginTime,String endTime) {
        
      /*  if (!ContextHolderUtils.isLogin()) {
            return "login/login";
        }*/
        
        List<JSONObject> result = fireEventServcie.getBaseDate(beginTime,endTime);
        
        return result;
    }
    
    
    @RequestMapping("/toAreaPage")
    private String toAreaPage() {
        
        /*  if (!ContextHolderUtils.isLogin()) {
            return "login/login";
        }*/
        
        return "alarm/alarm-region";
    }
    
    /**
     * @createDate 2017年3月29日上午10:14:53 
     * @author wangzhiwang
     * @return
     * @description 获取街道的详情
     */
    @RequestMapping(value = "/getAreaDate",method = RequestMethod.POST)
    @ResponseBody
    private List<JSONObject> getAreaDate(@RequestParam(required=true) Long id) {
        
        /*  if (!ContextHolderUtils.isLogin()) {
            return "login/login";
        }*/
        
        List<JSONObject> result = fireEventServcie.getAreaDate(id);
        
        return result;
    }
    
    /**
     * @createDate 2017年4月17日上午9:56:22 
     * @author wangzhiwang
     * @param streetId
     * @return 
     * @description 查询街道下所有警情
     */
    @RequestMapping(value = "/getStreetEvent",method = RequestMethod.GET)
    @ResponseBody
    private List<AppFireEvent> getStreetEvent(@RequestParam(required=true) Long streetId) {
        
        /*  if (!ContextHolderUtils.isLogin()) {
            return "login/login";
        }*/
        
        List<AppFireEvent> result = fireEventServcie.getStreetEvent(streetId);
        
        return result;
    }
    
    /**
     * @createDate 2017年4月17日上午9:56:39 
     * @author wangzhiwang
     * @param id
     * @return 
     * @description 通过id获取警情数据
     */
    @RequestMapping(value = "/getEvent",method = RequestMethod.GET)
    @ResponseBody
    private AppFireEvent getEvent(@RequestParam(required=true) Long id) {
        
        /*  if (!ContextHolderUtils.isLogin()) {
            return "login/login";
        }*/
        
        AppFireEvent result = fireEventServcie.getEventById(id);
        
        return result;
    }
}
