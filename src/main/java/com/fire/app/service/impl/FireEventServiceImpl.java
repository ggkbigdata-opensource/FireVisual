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

    @Override
    public List<JSONObject> getData() {
        
        
Date date = new Date();
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date); // 设置为当前时间
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1); // 设置为上一个月
        date = calendar.getTime();
        
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd"); 
        String currentDate = df.format(date);
        String[] arr = currentDate.split("-");
        GregorianCalendar calendar1 = new GregorianCalendar(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]), Integer.parseInt(arr[2]));//灵活的输入年份，月
        
        
        for (int i = 1; i < 13; i++) {
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
            calendar1.add(calendar.MONTH, -1);//获取上个月月份
            String[] date1 = sdf.format(calendar1.getTime()).split("-");
            
            //List<AppFireEvent> list = fireEventRepository.findDateToMonth(date1[0],date1[1]);
            
           // System.out.println(list);
            
        }
        
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("1", "1");
        jsonObject.put("1", "1");
        jsonObject.put("1", "1");
        jsonObject.put("1", "1");
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("13", "31");
        jsonObject2.put("31", "31");
        jsonObject2.put("331", "31");
        jsonObject2.put("31", "13");
        
        List<JSONObject> arrayList = new ArrayList<JSONObject>();
        arrayList.add(jsonObject);
        arrayList.add(jsonObject2);
        
        
        return null;
    }
    
}
