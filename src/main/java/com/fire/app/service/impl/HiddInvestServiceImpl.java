package com.fire.app.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.fire.app.domain.AppFireEvent;
import com.fire.app.domain.AppFireEventRepository;
import com.fire.app.domain.BsBuildingInfo;
import com.fire.app.domain.BsBuildingInfoRepository;
import com.fire.app.domain.CrCheckReportInfo;
import com.fire.app.domain.CrCheckReportInfoRepository;
import com.fire.app.domain.Street;
import com.fire.app.domain.StreetRepository;
import com.fire.app.service.FireEventService;
import com.fire.app.service.HiddInvestService;
import com.fire.app.util.DateUtil;

/**
 * @createDate 2017年3月28日下午4:09:02
 * @author wangzhiwang
 * @description
 */
@Service
public class HiddInvestServiceImpl implements HiddInvestService {

    @Autowired
    private BsBuildingInfoRepository buildingInfoRepository;
    @Autowired
    private StreetRepository streetRepository;
    @Autowired
    private CrCheckReportInfoRepository checkReportInfoRepository;

    @Override
    public List<JSONObject> getHidVersion() {

        ArrayList<JSONObject> result = new ArrayList<JSONObject>();

        List<Street> streets = streetRepository.findAll();
        for (Street street : streets) {
            
            int versionOne= 0;
            int versionTwo= 0;
            int versionThree= 0;
            int versionFour= 0;
            
            String reportNum=null;
            
            
            JSONObject obj = new JSONObject();
            List<BsBuildingInfo> infos = buildingInfoRepository.findByStreetId(street.getId());
            if (infos != null && !"".equals(infos)) {
                obj.put("buildingInfoNum", infos.size());
                reportNum=infos.get(0).getItemNumber();
            }else {
                obj.put("buildingInfoNum", 0);
            }

            List<CrCheckReportInfo> checkReportInfos = checkReportInfoRepository.findByReportNum(reportNum);
            
            for (CrCheckReportInfo crCheckReportInfo : checkReportInfos) {
                String riskLevel = crCheckReportInfo.getRiskLevel();
                if (riskLevel.contains("1")) {//隐患等级1
                    versionOne++;
                }
                if (riskLevel.contains("2")) {//隐患等级2
                    versionTwo++;
                }
                if (riskLevel.contains("3")) {//隐患等级3
                    versionThree++;
                }
                if (riskLevel.contains("4")) {//隐患等级4
                    versionFour++;
                }
            }
            
            obj.put("versionOne", versionOne);
            obj.put("versionOne", versionTwo);
            obj.put("versionOne", versionThree);
            obj.put("versionOne", versionFour);
            obj.put("streetId", street.getId());
            obj.put("streetName", street.getName());
            result.add(obj);

        }

        return result;
    }

    
    @Override
    public List<JSONObject> getDetailDate(Long streetId) {

        
        
        
        
        return null;
    }
    
    @Override
    public List<JSONObject> getInvestigateItem() {
        ArrayList<JSONObject> result = new ArrayList<JSONObject>();

        List<Street> streets = streetRepository.findAll();
        for (Street street : streets) {
            JSONObject obj = new JSONObject();

            result.add(obj);

        }

        return result;
    }


}
