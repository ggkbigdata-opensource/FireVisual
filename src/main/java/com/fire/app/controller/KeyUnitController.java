package com.fire.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.fire.app.common.App;
import com.fire.app.domain.BrowseRecord;
import com.fire.app.domain.BsBuildingInfo;
import com.fire.app.domain.User;
import com.fire.app.service.BrowseRecordService;
import com.fire.app.service.KeyUnitService;
import com.fire.app.util.ContextHolderUtils;

/**
 * @createDate 2017年3月28日下午3:57:31
 * @author wangzhiwang
 * @description
 */
@RequestMapping(value = "/app/keyUnit")
@Controller
public class KeyUnitController {

    @Autowired
    private KeyUnitService keyUnitService;
    @Autowired
    private BrowseRecordService browseRecordService;

    
    @RequestMapping()
    private String toFireEvent(HttpServletRequest request) {
        
        if (!ContextHolderUtils.isLogin()) {
            return "login/login";
        }
        
        User user = (User)ContextHolderUtils.getSession().getAttribute(App.USER_SESSION_KEY);
        
        List<JSONObject> result=  null;
        if (user!=null) {
            //获取最近浏览记录
          result = browseRecordService.findByUid(user.getUid());
        }
        
        request.setAttribute("result", result);
        
        
        return "keyUnit/keyUnit";
    }
    
    @RequestMapping(value = "/getUnit",method = RequestMethod.POST)
    @ResponseBody
    private List<JSONObject> getUnitByName(String name) {
        
        /*  if (!ContextHolderUtils.isLogin()) {
            return "login/login";
        }*/
        List<JSONObject> result = browseRecordService.findUnitByName(name);
        
        return result;
    }
}
