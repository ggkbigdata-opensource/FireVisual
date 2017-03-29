package com.fire.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fire.app.domain.Street;
import com.fire.app.service.StreetService;

/**
 * @createDate 2017年3月28日下午3:57:31
 * @author wangzhiwang
 * @description
 */
@RequestMapping("/app/street")
@Controller
public class StreetController {

    @Autowired
    private StreetService streetService;
    
    @RequestMapping("/findAll")
    @ResponseBody
    public List<Street> findALL() {
        
        List<Street> result = streetService.findAll();
        
        return result;
        
    }

    
}
