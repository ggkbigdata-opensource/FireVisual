package com.fire.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.fire.app.common.CommonResult;
import com.fire.app.service.FireEventService;
import com.fire.app.util.ContextHolderUtils;

/**
 * @createDate 2017年3月28日下午3:57:31
 * @author wangzhiwang
 * @description
 */
@RequestMapping("/fireEvent")
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
    public CommonResult main() {

        List<JSONObject> result = fireEventServcie.getData();

        return CommonResult.success(result);

    }
}
