package com.fire.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fire.app.domain.BsBuildingInfo;
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
    private String toFireEvent() {
        
        if (!ContextHolderUtils.isLogin()) {
            return "login/login";
        }
        
        return "keyUnit/keyUnit";
    }
    
    @RequestMapping(value = "/getUnit",method = RequestMethod.POST)
    @ResponseBody
    private List<BsBuildingInfo> getEvent(String name) {
        
        /*  if (!ContextHolderUtils.isLogin()) {
            return "login/login";
        }*/
        List<BsBuildingInfo> result = keyUnitService.findUnitByName(name);
        
        return result;
    }
}
