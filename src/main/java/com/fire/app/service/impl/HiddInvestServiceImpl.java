package com.fire.app.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fire.app.domain.BsBuildingInfo;
import com.fire.app.domain.BsBuildingInfoRepository;
import com.fire.app.domain.CrCheckReportInfo;
import com.fire.app.domain.CrCheckReportInfoRepository;
import com.fire.app.domain.CrCheckReportResultStat;
import com.fire.app.domain.CrCheckReportResultStatRepository;
import com.fire.app.domain.Street;
import com.fire.app.domain.StreetRepository;
import com.fire.app.service.HiddInvestService;

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
    @Autowired
    private CrCheckReportResultStatRepository checkReportResultStatRepository;

    @Override
    public List<JSONObject> getHidGrade() {

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
                
                CrCheckReportInfo checkReportInfos = checkReportInfoRepository.findByReportNum(reportNum);
                String riskLevel = checkReportInfos.getRiskLevel();
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
                
            }else {
                obj.put("buildingInfoNum", 0);
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

        ArrayList<JSONObject> result = new ArrayList<JSONObject>();
        List<BsBuildingInfo> infos = buildingInfoRepository.findByStreetId(streetId);
        for (BsBuildingInfo info : infos) {
            JSONObject obj = new JSONObject();
            CrCheckReportInfo checkReportInfos = checkReportInfoRepository.findByReportNum(info.getItemNumber());
            obj.put("riskLevel", checkReportInfos.getRiskLevel());

            List<CrCheckReportResultStat> stats  = checkReportResultStatRepository.findByReportNum(info.getItemNumber());
            int unqualifiedNum =0;//不合格项
            for (CrCheckReportResultStat stat : stats) {
                unqualifiedNum = unqualifiedNum + stat.getUnqualifiedNum();
            }
            obj.put("unqualifiedNum", unqualifiedNum);
            obj.put("ropertyCompanyName", info.getPropertyCompanyName());

            result.add(obj);
        }
        return result;
    }
    
    @Override
    public List<JSONObject> getInvestigateItem() {
        ArrayList<JSONObject> result = new ArrayList<JSONObject>();
        List<Object> list = checkReportResultStatRepository.findGroupByItemCode();
        DecimalFormat   df=new   DecimalFormat("0.00");   
        
        for (Object object : list) {
            JSONObject obj = new JSONObject();
            String str = JSONArray.toJSONString(object);//存入的数据为[1,1,"1","1"]  checkNum, unqualifiedNum itemCode itemName
            String[] data = str.replace("[", "").replace("]", "").replace("\"", "").split(",");

            
            int qualifiedNum = Integer.parseInt(data[0])-Integer.parseInt(data[1]);
            obj.put("qualifiedNum", qualifiedNum);
            obj.put("unqualifiedNum", data[1]);
            obj.put("itemCode", data[2]);
            obj.put("itemName", data[3]);
            obj.put("qualifiedPercent", df.format((float)qualifiedNum/Integer.parseInt(data[0])));
            
            result.add(obj);
            
        }

        return result;
    }


}
