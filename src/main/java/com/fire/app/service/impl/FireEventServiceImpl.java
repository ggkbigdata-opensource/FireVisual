package com.fire.app.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.fire.app.domain.AppFireEvent;
import com.fire.app.domain.AppFireEventRepository;
import com.fire.app.domain.Block;
import com.fire.app.domain.BlockRepository;
import com.fire.app.domain.Street;
import com.fire.app.domain.StreetRepository;
import com.fire.app.service.FireEventService;

/**
 * @createDate 2017年3月28日下午4:09:02
 * @author wangzhiwang
 * @description
 */
@Service
public class FireEventServiceImpl implements FireEventService {

    @Autowired
    private AppFireEventRepository fireEventRepository;
    @Autowired
    private StreetRepository streetRepository;
    @Autowired
    private BlockRepository blockRepository;

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

        // 用于存储每个月的数值
        HashMap<String, Integer> oneSum = new HashMap<String, Integer>();
        HashMap<String, Integer> twoSum = new HashMap<String, Integer>();
        HashMap<String, Integer> threeSum = new HashMap<String, Integer>();

        for (int i = 1; i < 13; i++) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            calendar1.add(calendar.MONTH, -1);// 获取上个月月份
            String[] date1 = sdf.format(calendar1.getTime()).split("-");

            List<AppFireEvent> list = fireEventRepository.findDateToMonth(date1[0], date1[1]);
            int oned = 0;
            int twod = 0;
            int threed = 0;
            for (AppFireEvent event : list) {
                if ("原始警情".equals(event.getFireType())) {
                    oned++;
                }
                if ("冒烟警情".equals(event.getFireType())) {
                    twod++;
                }
                if ("确认警情".equals(event.getFireType())) {
                    threed++;
                }
            }
            oneSum.put(i+"月", oned);
            twoSum.put(i+"月", twod);
            threeSum.put(i+"月", threed);

        }

        JSONObject one = new JSONObject();// 原始警情
        JSONObject two = new JSONObject();// 冒烟警情
        JSONObject three = new JSONObject();// 确认警情

        one.put("type", "原始警情");
        one.put("value", oneSum);
        one.put("unit", "起");

        two.put("type", "冒烟警情");
        two.put("value", twoSum);
        one.put("unit", "起");

        three.put("type", "确认警情");
        three.put("value", threeSum);
        one.put("unit", "起");

        List<JSONObject> result = new ArrayList<JSONObject>();
        result.add(one);
        result.add(two);
        result.add(three);

        return result;
    }

    @Override
    public List<JSONObject> getBaseDate(String type, String beginTime, String endTime) {
        //获取所有的街道
        List<Street> streets = streetRepository.findAll();
        if (streets!= null && streets.size()>0) {
            for (Street street : streets) {
                //获取街道下对应的社区
                List<Block> blocks = blockRepository.findByStreetId(street.getId());
                
                JSONObject streetResult = new JSONObject();
                
                Integer value = fireEventRepository.findStreetData(type,beginTime,endTime,street.getId());
                System.out.println(value);
                
                
                
            }
            
        }
        
        
        return null;
    }

}
