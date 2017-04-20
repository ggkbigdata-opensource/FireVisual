package com.fire.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.fire.app.domain.AppFireEventRepository;
import com.fire.app.domain.BrowseRecord;
import com.fire.app.domain.BrowseRecordRepository;
import com.fire.app.domain.BsBuildingInfoRepository;
import com.fire.app.service.BrowseRecordService;

/**
 * @createDate 2017年3月28日下午4:09:02
 * @author wangzhiwang
 * @description
 */
@Service
public class BrowseRecordServiceImpl implements BrowseRecordService {

    @Autowired
    private BrowseRecordRepository browseRecordRepository;

    @Override
    public List<JSONObject> findByUid(Long uid) {

        List<BrowseRecord> result= browseRecordRepository.findByUid(uid);
        
        
        return null;
    }
    

}
