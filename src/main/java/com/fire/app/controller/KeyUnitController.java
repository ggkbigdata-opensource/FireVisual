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
import com.fire.app.domain.BsBuildingInfo;
import com.fire.app.domain.User;
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

    @RequestMapping()
    private String toKeyUnitPage() {
        
        if (!ContextHolderUtils.isLogin()) {
            return "login/login";
        }
        
        return "keyUnit/keyUnit";
    }
    
    
    @RequestMapping(value="/recent")
    @ResponseBody
    private List<JSONObject> toFireEvent(HttpServletRequest request) {
        
        User user = (User)ContextHolderUtils.getSession().getAttribute(App.USER_SESSION_KEY);
        
        List<JSONObject> result=  null;
        if (user!=null) {
            //获取最近浏览记录
          result = keyUnitService.findByUid(user.getUid());
        }
        
        request.setAttribute("result", result);
        
        
        return result;
    }
    
    /**
     * @createDate 2017年5月9日下午5:44:47 
     * @author wangzhiwang
     * @param name
     * @return 
     * @description 
     */
    @RequestMapping(value = "/search",method = RequestMethod.POST)
    @ResponseBody
    private List<JSONObject> getUnitByName(String name) {
        
        List<JSONObject> result = keyUnitService.findUnitByName(name);
        
        return result;
    }
    
    /**
     * @createDate 2017年5月9日下午5:44:47 
     * @author wangzhiwang
     * @param name
     * @return 
     * @description 查看建筑主体详情
     */
    @RequestMapping(value = "/unit",method = RequestMethod.GET)
    private String getUnit(HttpServletRequest request ,Long id) {
        
        BsBuildingInfo result = keyUnitService.findById(id);
        
        request.setAttribute("info", result);
        
        return "keyUnit/buildingProfile";
    }
}
