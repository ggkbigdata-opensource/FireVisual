package com.fire.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.fire.app.service.SituationService;
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
}
