package com.fire.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.fire.app.common.CommonResult;
import com.fire.app.service.FireEventServcie;

/**
 * @createDate 2017年3月28日下午3:57:31
 * @author wangzhiwang
 * @description
 */
@RequestMapping("/fireEvent")
public class FireEventController {

    @Autowired
    private FireEventServcie fireEventServcie;
    
    
    @RequestMapping("/main")
    public CommonResult main() {
        
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("1", "1");
        jsonObject.put("1", "1");
        jsonObject.put("1", "1");
        jsonObject.put("1", "1");
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("13", "31");
        jsonObject2.put("31", "31");
        jsonObject2.put("331", "31");
        jsonObject2.put("31", "13");
        
        List<JSONObject> arrayList = new ArrayList<JSONObject>();
        arrayList.add(jsonObject);
        arrayList.add(jsonObject2);
        
        
        
       return CommonResult.success(arrayList);
    }
}
