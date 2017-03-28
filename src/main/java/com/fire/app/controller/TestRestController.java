package com.fire.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.fire.app.domain.AppFireEventRepository;
import com.fire.app.domain.AppPunishment;
import com.fire.app.domain.AppPunishmentRepository;
import com.fire.app.domain.BsBuildingInfoRepository;
import com.fire.app.domain.BsEstateManagerRepository;
import com.fire.app.domain.BsFireSystemRepository;
import com.fire.app.domain.BsKeyPartRepository;
import com.fire.app.domain.BsMainFunctionRepository;

/**
 * @fileName TestRestController.java
 * @author csk
 * @createTime 2017年3月28日 下午12:30:21
 * @version 1.0
 * @function
 */
@RestController
public class TestRestController {
    
    @Autowired
    private AppFireEventRepository fireEventRepo;
    
    @Autowired
    private AppPunishmentRepository punishmentRepo;
    
    @Autowired
    private BsBuildingInfoRepository buidlingInfoRepo;
    
    @Autowired
    private BsEstateManagerRepository estateManagerRepo;
    
    @Autowired
    private BsFireSystemRepository fireSystemRepo;
    
    @Autowired
    private BsKeyPartRepository keyPartRepo;
    
    @Autowired
    private BsMainFunctionRepository mainFunctionRepo;
    
    @RequestMapping(value={"testEntity"},method = RequestMethod.GET)
    public JSONObject TestEntity(){
        JSONObject result = new JSONObject();
        result.put("fireEvents", fireEventRepo.findAll());
        result.put("punishments", punishmentRepo.findAll());
        result.put("buidlingInfo", buidlingInfoRepo.findAll());
        result.put("estateManager", estateManagerRepo.findAll());
        result.put("fireSystem", fireSystemRepo.findAll());
        result.put("keyPart", keyPartRepo.findAll());
        return result;
    }
    
}
