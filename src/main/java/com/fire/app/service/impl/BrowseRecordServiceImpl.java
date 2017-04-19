package com.fire.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.fire.app.domain.AppFireEventRepository;
import com.fire.app.service.BrowseRecordService;

/**
 * @createDate 2017年3月28日下午4:09:02
 * @author wangzhiwang
 * @description
 */
@Service
public class BrowseRecordServiceImpl implements BrowseRecordService {

    @Autowired
    private AppFireEventRepository fireEventRepository;

    @Override
    public List<JSONObject> findByUid() {

        
        
        
        return null;
    }
    

}
