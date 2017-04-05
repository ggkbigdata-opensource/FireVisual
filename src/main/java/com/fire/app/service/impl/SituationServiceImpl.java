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
import com.fire.app.domain.BsBuildingInfo;
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
        Date before = calendar.getTime();
        calendar.setTime(new Date()); // 设置为当前时间
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1); // 设置前3个月
        Date now = calendar.getTime();

        String time1 = DateUtil.formatDate(before, "yyyy-MM") + "-01";
        String time2 = DateUtil.formatDate(now, "yyyy-MM") + "-30";

        Date newYear = DateUtil.parseDate(time1);
        Date nowTime = DateUtil.parseDate(time2);

        List<Street> streets = streetRepository.findAll();
        List<JSONObject> result = new ArrayList<JSONObject>();
        for (Street street : streets) {
            JSONObject obj = new JSONObject();
            int version = 1; // 等级，，，即不合格数
            // 查询警情数据
            List<AppFireEvent> events = fireEventRepository.findStreetData(newYear, nowTime, street.getName());
            // 查询执法数据
            List<AppPunishment> sealUp = punishmentRepository.findSealUpStreetData(newYear, nowTime, street.getName());
            List<AppPunishment> notSealUp = punishmentRepository.findStreetData(newYear, nowTime, street.getName());

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

            // 获取街道的重要单位
            List<BsBuildingInfo> infos = buildingInfoRepository.findByStreetId(street.getId());
            if (infos != null && infos.size() > 0) {
                obj.put("buildingInfoNum", infos.size());
                // 获取检测报告的数据
                CrCheckReportInfo reportInfo = checkReportInfoRepository.findByReportNum(infos.get(0).getItemNumber());
                int num = 0;// 危险等级超过4的数量
                if (reportInfo != null) {
                    if (reportInfo.getRiskLevel().contains("4")) {
                        num++;
                    }
                }

                if (num > checkValue) {
                    version++;
                }
            } else {
                obj.put("buildingInfoNum", 0);
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

}
