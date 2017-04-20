package com.fire.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.fire.app.domain.BrowseRecord;
import com.fire.app.domain.BrowseRecordRepository;
import com.fire.app.domain.BsBuildingInfo;
import com.fire.app.domain.BsBuildingInfoRepository;
import com.fire.app.domain.CrCheckReportInfo;
import com.fire.app.domain.CrCheckReportInfoRepository;
import com.fire.app.domain.Street;
import com.fire.app.domain.StreetRepository;
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
    @Autowired
    private BsBuildingInfoRepository buildingInfoRepository;
    @Autowired
    private CrCheckReportInfoRepository reportInfoRepository;
    @Autowired
    private StreetRepository streetRepository;

    @Override
    public List<JSONObject> findByUid(Long uid) {

        List<BrowseRecord> records = browseRecordRepository.findByUid(uid);

        long[] arr = new long[3];

        int i = 0;
        for (BrowseRecord browseRecord : records) {
            arr[i] = browseRecord.getBuildingInfoId();
            if (i<3) {
                i++;
            }else{
                break;
            }
        }

        List<JSONObject> result = new ArrayList<JSONObject>();
        
        
        List<BsBuildingInfo> buildings =  buildingInfoRepository.findByIds(arr);
        
        for (BsBuildingInfo buildingInfo : buildings) {
            JSONObject obj = new JSONObject();
            //查询隐患等级
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

    /* (non-Javadoc)
     * @see com.fire.app.service.BrowseRecordService#findUnitByName(java.lang.String)
     */
    @Override
    public List<JSONObject> findUnitByName(String name) {
        // TODO Auto-generated method stub
        return null;
    }

}
