package com.fire.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.fire.app.service.FireEventService;
import com.fire.app.util.ContextHolderUtils;

/**
 * @createDate 2017年3月28日下午3:57:31
 * @author wangzhiwang
 * @description 统计概况
 */
@RequestMapping("/app/statistical")
@Controller
public class StatisticalController {


    @RequestMapping("")
    private String toMainPage() {
        
        if (!ContextHolderUtils.isLogin()) {
            return "login/login";
        }
        
        return "statisticalData/statisticalData";
    }
    @RequestMapping("/toEventOne")
    private String toEventPageOne() {
        
        if (!ContextHolderUtils.isLogin()) {
            return "login/login";
        }
        
        return "statisticalData/statisticalData";
    }
    @RequestMapping("/toEventTwo")
    private String toEventPageTwo() {
        
        if (!ContextHolderUtils.isLogin()) {
            return "login/login";
        }
        
        return "statisticalData/statisticalData";
    }
    @RequestMapping("/toPunishOne")
    private String toPunishmentOne() {
        
        if (!ContextHolderUtils.isLogin()) {
            return "login/login";
        }
        
        return "statisticalData/statisticalData";
    }
    @RequestMapping("/toPunishTwo")
    private String toPunishmentTwo() {
        
        if (!ContextHolderUtils.isLogin()) {
            return "login/login";
        }
        
        return "statisticalData/statisticalData";
    }
    
}
