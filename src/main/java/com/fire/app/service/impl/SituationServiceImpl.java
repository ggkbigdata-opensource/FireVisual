package com.fire.app.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.fire.app.domain.AppFireEvent;
import com.fire.app.domain.AppFireEventRepository;
import com.fire.app.domain.AppPunishment;
import com.fire.app.domain.AppPunishmentRepository;
import com.fire.app.domain.BsBuildingInfoRepository;
import com.fire.app.domain.CrCheckReportInfo;
import com.fire.app.domain.CrCheckReportInfoRepository;
import com.fire.app.domain.Street;
import com.fire.app.domain.StreetRepository;
import com.fire.app.service.SituationService;
import com.fire.app.util.DateUtil;

/**
 * @createDate 2017年3月28日下午4:09:02
 * @author wangzhiwang
 * @description
 */
@Service
public class SituationServiceImpl implements SituationService {

    @Autowired
    private AppFireEventRepository fireEventRepository;
    @Autowired
    private AppPunishmentRepository punishmentRepository;
    @Autowired
    private StreetRepository streetRepository;
    @Autowired
    private BsBuildingInfoRepository buildingInfoRepository;
    @Autowired
    private CrCheckReportInfoRepository checkReportInfoRepository;
    // @Autowired
    // private CrCheckReportResultStatRepository
    // checkReportResultStatRepository;

    @Value("${punishment}")
    private Integer punishmentValue;
    @Value("${fireEvent}")
    private Integer fireEventValue;
    @Value("${check}")
    private Integer checkValue;

    @Override
    public List<JSONObject> getAllStreetSituation() {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date()); // 设置为当前时间
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 3); // 设置为上一个月
        Date beginTime = calendar.getTime();
        calendar.setTime(new Date()); // 设置为当前时间
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1); // 设置前3个月
        Date endTime = calendar.getTime();

        String time1 = DateUtil.formatDate(beginTime, "yyyy-MM") + "-01";
        String time2 = DateUtil.formatDate(endTime, "yyyy-MM") + "-31";

        Date bTime = DateUtil.parseDate(time1);
        Date eTime = DateUtil.parseDate(time2);

        List<Street> streets = streetRepository.findAll();
        List<JSONObject> result = new ArrayList<JSONObject>();
        for (Street street : streets) {
            JSONObject obj = new JSONObject();
            int version = 1; // 等级，，，即不合格数
            // 查询警情数据----这里只需要冒烟，确认两个类型，   确认就是火灾
            //List<AppFireEvent> events = fireEventRepository.findStreetData(newYear, nowTime, street.getId());
            List<String> types = new ArrayList<String>();
            types.add("火灾");
            types.add("冒烟");
            List<AppFireEvent> events = fireEventRepository.findByStreetId(street.getId(), bTime, eTime, types);
            // 查询执法数据
            List<AppPunishment> sealUp = punishmentRepository.findSealUpStreetData(bTime, eTime, street.getId());
            List<AppPunishment> notSealUp = punishmentRepository.findStreetData(bTime, eTime, street.getId());

            if (streets != null && streets.size() > 0) {
                obj.put("eventNum", events.size());
                if (events.size() > fireEventValue) {
                    version++;
                }
            } else {
                obj.put("eventNum", 0);
            }
            int punishNum = 0;
            if (sealUp != null && sealUp.size() > 0) {
                punishNum = punishNum + sealUp.size();
            }
            if (notSealUp != null && notSealUp.size() > 0) {
                punishNum = punishNum + notSealUp.size();
            }
            obj.put("punishNum", punishNum);

            // 获取街道的重要单位---检测报告数据
            //List<BsBuildingInfo> infos = buildingInfoRepository.findByStreetId(street.getId());
            obj.put("checkInfoNum", 0);
            List<CrCheckReportInfo> infos = checkReportInfoRepository.findByStreetId(street.getId());
            for (CrCheckReportInfo reportInfo : infos) {
                obj.put("checkInfoNum", infos.size());
                // 获取检测报告的数据
                int num = 0;// 危险等级超过4的数量
                if (reportInfo != null&&reportInfo.getRiskLevel()!=null) {
                    if (reportInfo.getRiskLevel().contains("4")) {
                        num++;
                    }
                }

                if (num > checkValue) {
                    version++;
                }
            }
                
            if (punishNum > punishmentValue) {
                version++;
            }

            obj.put("version", version);
            obj.put("streetId", street.getId());
            obj.put("streetName", street.getName());

            result.add(obj);
        }

        return result;
    }

    @Override
    public List<JSONObject> getStreetEvent(Long streetId) {
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date()); // 设置为当前时间
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 3); // 设置为上一个月
        Date beginTime = calendar.getTime();
        calendar.setTime(new Date()); // 设置为当前时间
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1); // 设置前3个月
        Date endTime = calendar.getTime();

        String time1 = DateUtil.formatDate(beginTime, "yyyy-MM") + "-01";
        String time2 = DateUtil.formatDate(endTime, "yyyy-MM") + "-31";

        Date bTime = DateUtil.parseDate(time1);
        Date eTime = DateUtil.parseDate(time2);
        
        List<String> types = new ArrayList<String>();
        types.add("火灾");
        types.add("冒烟");
        List<AppFireEvent> events = fireEventRepository.findByStreetId(streetId, bTime, eTime, types);
        
        List<JSONObject> result = new ArrayList<JSONObject>();
        
        for (AppFireEvent event : events) {
            JSONObject obj = new JSONObject();

            String time = null;
            if (event.getOccurTime() != null) {
                time = DateUtil.formatDate(event.getOccurTime(), "yyyy/MM/dd HH:mm");
            }
            obj.put("time", time);
            obj.put("id", event.getId());

            if ("冒烟".equals(event.getFireType())) {
                obj.put("type_change", "警情类型：冒烟");
            }else{
                obj.put("type_change", "警情类型：确认");
            }

            result.add(obj);
        }
        
        return result;
    }

    @Override
    public List<JSONObject> getStreetPunish(Long streetId) {
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date()); // 设置为当前时间
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 3); // 设置为上一个月
        Date beginTime = calendar.getTime();
        calendar.setTime(new Date()); // 设置为当前时间
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1); // 设置前3个月
        Date endTime = calendar.getTime();

        String time1 = DateUtil.formatDate(beginTime, "yyyy-MM") + "-01";
        String time2 = DateUtil.formatDate(endTime, "yyyy-MM") + "-31";

        Date bTime = DateUtil.parseDate(time1);
        Date eTime = DateUtil.parseDate(time2);
        
        // 查询执法数据
        List<AppPunishment> sealUp = punishmentRepository.findSealUpStreetData(bTime, eTime, streetId);
        List<AppPunishment> notSealUp = punishmentRepository.findStreetData(bTime, eTime, streetId);
        
        List<JSONObject> list = new ArrayList<JSONObject>();
        
        
        for (AppPunishment punishment : sealUp) {
            JSONObject obj = new JSONObject();
            obj.put("id", punishment.getId());

            if ("行政罚款".equals(punishment.getPunishMethod())) {
                obj.put("type_change", "执法类型：行政罚款");
            } else if ("行政拘留".equals(punishment.getPunishMethod())) {
                obj.put("type_change", "执法类型：行政拘留");
            } else if ("刑事拘留".equals(punishment.getPunishMethod())){
                obj.put("type_change", "执法类型：刑事拘留");
            } else if ("临时查封".equals(punishment.getPunishMethod())){
                obj.put("type_change", "执法类型：临时查封");
            } else {
                obj.put("type_change", "执法类型：三停");
            }

            Date time = null;
            if ("临时查封".equals(punishment.getPunishMethod())) {
                time = punishment.getSealUpTimeBegin();
            } else {
                time = punishment.getExecuteTime();
            }
            
            obj.put("id", punishment.getId());

            obj.put("time", DateUtil.formatDate(time, "yyyy/MM/dd HH:mm"));

            obj.put("dutyPerson", punishment.getDutyPersonName());
            // 传上来的参数

            list.add(obj);
        }
        for (AppPunishment punishment : notSealUp) {
            JSONObject obj = new JSONObject();
            obj.put("id", punishment.getId());

            if ("行政罚款".equals(punishment.getPunishMethod())) {
                obj.put("type_change", "行罚");
            } else if ("行政拘留".equals(punishment.getPunishMethod())) {
                obj.put("type_change", "行拘");
            } else if ("刑事拘留".equals(punishment.getPunishMethod())){
                obj.put("type_change", "刑拘");
            } else if ("临时查封".equals(punishment.getPunishMethod())){
                obj.put("type_change", "临封");
            } else {
                obj.put("type_change", "三停");
            }

            obj.put("id", punishment.getId());

            Date time = null;
            if ("临时查封".equals(punishment.getPunishMethod())) {
                time = punishment.getSealUpTimeBegin();
            } else {
                time = punishment.getExecuteTime();
            }
            
            obj.put("time", DateUtil.formatDate(time, "yyyy/MM/dd HH:mm"));

            obj.put("dutyPerson", punishment.getDutyPersonName());
            // 传上来的参数

            list.add(obj);
        }
        
        return list;
    }

}
