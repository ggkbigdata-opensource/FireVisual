package com.fire.app.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.fire.app.common.App;
import com.fire.app.domain.BrowseRecord;
import com.fire.app.domain.BrowseRecordRepository;
import com.fire.app.domain.BsBuildingInfo;
import com.fire.app.domain.BsBuildingInfoRepository;
import com.fire.app.domain.CrCheckReportInfo;
import com.fire.app.domain.CrCheckReportInfoRepository;
import com.fire.app.domain.Street;
import com.fire.app.domain.StreetRepository;
import com.fire.app.domain.User;
import com.fire.app.service.KeyUnitService;
import com.fire.app.util.ContextHolderUtils;

/**
 * @createDate 2017年3月28日下午4:08:47
 * @author wangzhiwang
 * @description
 */
@Service
public class KeyUnitServiceImpl implements KeyUnitService{

    @Autowired
    private BrowseRecordRepository browseRecordRepository;
    @Autowired
    private BsBuildingInfoRepository buildingInfoRepository;
    @Autowired
    private CrCheckReportInfoRepository reportInfoRepository;
    @Autowired
    private StreetRepository streetRepository;
    
    @Override
    public List<JSONObject> findByUid(Long uid) {
        List<BrowseRecord> records = browseRecordRepository.findByUid(uid);
        
        List<JSONObject> result = new ArrayList<JSONObject>();
        
        int i = 0;
        for (BrowseRecord browseRecord : records) {
            if (i < 3) {
                i++;
            } else {
                break;
            }
            
            BsBuildingInfo buildingInfo = buildingInfoRepository.findOne(browseRecord.getBuildingInfoId());
            JSONObject obj = new JSONObject();
            // 查询隐患等级
            CrCheckReportInfo info = reportInfoRepository.findByReportNum(buildingInfo.getItemNumber());
            obj.put("id", buildingInfo.getId());
            obj.put("unitName", buildingInfo.getPropertyCompanyName());
            Street street = streetRepository.getOne(buildingInfo.getStreetId());
            obj.put("streetName", street.getName());
            obj.put("streetId", street.getId());
            obj.put("riskLevel", info.getRiskLevel());
            obj.put("constructionClass", buildingInfo.getConstructionClass());
            obj.put("constructionCategory", buildingInfo.getConstructionCategory());

            result.add(obj);
            
        }
        return result;
    }

    @Override
    public List<JSONObject> findUnitByName(String name) {

        List<BsBuildingInfo> buildings = buildingInfoRepository.findByPropertyCompanyName(name);

        List<JSONObject> result = new ArrayList<JSONObject>();

        for (BsBuildingInfo buildingInfo : buildings) {
            JSONObject obj = new JSONObject();
            // 查询隐患等级
            CrCheckReportInfo info = reportInfoRepository.findByReportNum(buildingInfo.getItemNumber());
            if (info!=null) {
                obj.put("id", buildingInfo.getId());
                obj.put("unitName", buildingInfo.getPropertyCompanyName());
                Street street = streetRepository.getOne(buildingInfo.getStreetId());
                obj.put("streetName", street.getName());
                obj.put("streetId", street.getId());
                obj.put("riskLevel", info.getRiskLevel());
                obj.put("constructionClass", buildingInfo.getConstructionClass());
                obj.put("constructionCategory", buildingInfo.getConstructionCategory());

                result.add(obj);
            }
        }

        return result;
    }

    @Override
    public BsBuildingInfo findById(Long id) {
        BsBuildingInfo buildingInfo = buildingInfoRepository.findOne(id);
        
        User user = (User)ContextHolderUtils.getSession().getAttribute(App.USER_SESSION_KEY);
        
        if (user!=null) {
            
            //判断是否已经存在 的信息
            BrowseRecord record = browseRecordRepository.findByBuildingInfoId(id);
            if (record!=null&&!"".equals(record)) {
                record.setBrowseTime(new Date());
                browseRecordRepository.save(record);
            }else {
              //删除最久远的数据
                List<BrowseRecord> records = browseRecordRepository.findByUid(user.getUid());
                
                if (records.size()>3) {
                    BrowseRecord browseRecord = records.get(records.size()-1);
                    browseRecordRepository.delete(browseRecord.getId());
                }
                //添加刚点击的数据
                BrowseRecord record1 = new BrowseRecord();
                record1.setBuildingInfoId(buildingInfo.getId());
                record1.setUid(user.getUid());
                record1.setBrowseTime(new Date());
                browseRecordRepository.save(record1);
            }
            
            
        }
        return buildingInfo;
        
    }

    @Override
    public BsBuildingInfo findBuildingInfoById(Long id) {

        BsBuildingInfo info = buildingInfoRepository.findOne(id);
        
        return info;
    }

}
