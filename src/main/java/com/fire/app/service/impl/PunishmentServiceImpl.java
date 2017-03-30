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
import com.fire.app.domain.AppPunishment;
import com.fire.app.domain.AppPunishmentRepository;
import com.fire.app.service.PunishmentService;

/**
 * @createDate 2017年3月28日下午4:09:02
 * @author wangzhiwang
 * @description
 */
@Service
public class PunishmentServiceImpl implements PunishmentService {

    @Autowired
    private AppPunishmentRepository punishmentRepository;
    
    @SuppressWarnings("static-access")
    @Override
    public List<JSONObject> getData() {
        Date date = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date); // 设置为当前时间
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1); // 设置为上一个月
        date = calendar.getTime();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = df.format(date);
        String[] arr = currentDate.split("-");
        GregorianCalendar calendar1 = new GregorianCalendar(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]),
                Integer.parseInt(arr[2]));// 灵活的输入年份，月

        List<JSONObject> oneSum = new ArrayList<JSONObject>();
        List<JSONObject> twoSum = new ArrayList<JSONObject>();
        List<JSONObject> threeSum = new ArrayList<JSONObject>();
        List<JSONObject> fourSum = new ArrayList<JSONObject>();
        List<JSONObject> fiveSum = new ArrayList<JSONObject>();

        for (int i = 1; i < 13; i++) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            calendar1.add(calendar.MONTH, -1);// 获取上个月月份
            String[] date1 = sdf.format(calendar1.getTime()).split("-");

            //获取临时查封
            List<AppPunishment> seals = punishmentRepository.findSealDateToMonth(date1[0], date1[1]);
            int seald = seals.size();
            
            //获取行政执法，行政拘留，刑事拘留，三停
            List<AppPunishment> list = punishmentRepository.findDateToMonth(date1[0], date1[1]);
            int oned = 0;//行政执法
            int twod = 0;//行政拘留
            int threed = 0;//刑事拘留
            int fourd = 0;//三停
            for (AppPunishment punish : list) {
                if ("行政执法".equals(punish.getPunishMethod())) {
                    oned++;
                }
                if ("行政拘留".equals(punish.getPunishMethod())) {
                    twod++;
                }
                if ("刑事拘留".equals(punish.getPunishMethod())) {
                    threed++;
                }
                if ("三停".equals(punish.getPunishMethod())) {
                    fourd++;
                }
            }

            JSONObject one = new JSONObject();//行政执法
            one.put("month", date1[1] + "月");
            one.put("value", oned);

            JSONObject two = new JSONObject();//行政拘留
            two.put("month", date1[1] + "月");
            two.put("value", twod);

            JSONObject three = new JSONObject();//刑事拘留
            three.put("month", date1[1] + "月");
            three.put("value", threed);
            
            JSONObject four = new JSONObject(); //三停
            four.put("month", date1[1] + "月");
            four.put("value", fourd);
            
            JSONObject five = new JSONObject();   //临时查封
            five.put("month", date1[1] + "月");
            five.put("value", seald);

            oneSum.add(one);
            twoSum.add(two);
            threeSum.add(three);
            fourSum.add(four);
            fiveSum.add(five);

        }

        JSONObject one = new JSONObject();// 
        JSONObject two = new JSONObject();// 
        JSONObject three = new JSONObject();// 
        JSONObject four = new JSONObject();// 
        JSONObject five = new JSONObject();// 
     
        one.put("type", "行政执法");
        one.put("unit", "起");
        one.put("data", oneSum);

        two.put("type", "行政拘留");
        two.put("unit", "起");
        two.put("data", twoSum);

        three.put("type", "刑事拘留");
        three.put("unit", "起");
        three.put("data", threeSum);
        
        four.put("type", "三停");
        four.put("unit", "起");
        four.put("data", fourSum);
        
        five.put("type", "临时查封");
        five.put("unit", "起");
        five.put("data", fiveSum);

        List<JSONObject> result = new ArrayList<JSONObject>();
        result.add(one);
        result.add(two);
        result.add(three);
        result.add(four);
        result.add(five);

        return result;
    }

    @Override
    public List<JSONObject> getAreaDate(Long id) {
        return null;
    }

    @Override
    public List<JSONObject> getBaseDate(String beginTime, String endTime) {
        return null;
    }

    

}

