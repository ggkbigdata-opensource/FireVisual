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

    @Override
    public List<JSONObject> getHidVersion() {

        ArrayList<JSONObject> result = new ArrayList<JSONObject>();

        List<Street> streets = streetRepository.findAll();
        for (Street street : streets) {
            JSONObject obj = new JSONObject();
            List<BsBuildingInfo> infos = buildingInfoRepository.findByStreetId(street.getId());
            if (infos != null && !"".equals(infos)) {
                obj.put("emphasisSum", infos.size());
            }else {
                obj.put("emphasisSum", 0);
            }

            obj.put("versionOne", street.getId());
            obj.put("versionOne", street.getId());
            obj.put("versionOne", street.getId());
            obj.put("versionOne", street.getId());
            obj.put("streetId", street.getId());
            result.add(obj);

        }

        return result;
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
