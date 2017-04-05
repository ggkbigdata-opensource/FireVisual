package com.fire.app.service.impl;

import java.text.DecimalFormat;
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
import com.fire.app.domain.Street;
import com.fire.app.domain.StreetRepository;
import com.fire.app.service.FireEventService;
import com.fire.app.util.DateUtil;

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

        for (int i = 1; i < 13; i++) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            calendar1.add(calendar.MONTH, -1);// 获取上个月月份
            String[] date1 = sdf.format(calendar1.getTime()).split("-");

            List<AppFireEvent> list = fireEventRepository.findDateToMonth(date1[0], date1[1]);
            int oned = 0;
            int twod = 0;
            int threed = 0;
            for (AppFireEvent event : list) {
                if ("原始".equals(event.getFireType())) {
                    oned++;
                }
                if ("冒烟".equals(event.getFireType())) {
                    twod++;
                }
                if ("确认".equals(event.getFireType())) {
                    threed++;
                }
            }

            JSONObject one = new JSONObject();
            one.put("month", date1[1] + "月");
            one.put("value", oned);

            JSONObject two = new JSONObject();
            two.put("month", date1[1] + "月");
            two.put("value", twod);

            JSONObject three = new JSONObject();
            three.put("month", date1[1] + "月");
            three.put("value", threed);

            oneSum.add(one);
            twoSum.add(two);
            threeSum.add(three);

        }

        JSONObject one = new JSONObject();// 原始警情
        JSONObject two = new JSONObject();// 冒烟警情
        JSONObject three = new JSONObject();// 确认警情

        one.put("type", "原始警情");
        one.put("unit", "起");
        one.put("data", oneSum);

        two.put("type", "冒烟警情");
        two.put("unit", "起");
        two.put("data", twoSum);

        three.put("type", "确认警情");
        three.put("unit", "起");
        three.put("data", threeSum);

        List<JSONObject> result = new ArrayList<JSONObject>();
        result.add(one);
        result.add(two);
        result.add(three);

        return result;
    }

    @Override
    public List<JSONObject> getBaseDate(String beginTime, String endTime) {
        // 获取所有的街道
        List<Street> streets = streetRepository.findAll();
        List<JSONObject> result = new ArrayList<JSONObject>();
        if (streets != null && streets.size() > 0) {
            for (Street street : streets) {
                // 获取街道下对应的社区
                // List<Block> blocks =
                // blockRepository.findByStreetId(street.getId());

                Date bTime = DateUtil.parse(beginTime + "-01");
                Date eTime = DateUtil.parse(endTime + "-30");

                int primitiveNow = 0;
                int smokingNow = 0;
                int affirmNow = 0;
                
                double lossNow = 0;
                int hurtNow = 0;
                int deadNow = 0;
                
                int primitiveBefore = 0;
                int smokingBefore = 0;
                int affirmBefore = 0;
                
                double lossBefore = 0;
                int hurtBefore = 0;
                int deadBefore = 0;
                
                DecimalFormat   df=new   DecimalFormat("0.00");   
                
                List<AppFireEvent> nowValue = fireEventRepository.findStreetData(bTime, eTime, street.getName());
                
                for (AppFireEvent appFireEvent : nowValue) {
                    if ("原始警情".equals(appFireEvent.getFireType())) {
                        primitiveNow++;
                    }
                    if ("冒烟警情".equals(appFireEvent.getFireType())) {
                        smokingNow++;
                    }
                    if ("确认警情".equals(appFireEvent.getFireType())) {
                        affirmNow++;
                    }
                    
                    lossNow = appFireEvent.getLoss()+lossNow;
                    hurtNow = appFireEvent.getHurtNum()+hurtNow;
                    deadNow = appFireEvent.getDeadNum()+deadNow;
                    
                }
                
                
                
                // 同比
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(bTime);
                calendar.add(Calendar.YEAR, -1);// 当前时间减去一年，即一年前的时间
                bTime = calendar.getTime();

                calendar.setTime(eTime);
                calendar.add(Calendar.YEAR, -1);// 当前时间减去一年，即一年前的时间
                eTime = calendar.getTime();

                List<AppFireEvent> beforeValue = fireEventRepository.findStreetData(bTime, eTime, street.getName());
                
                for (AppFireEvent appFireEvent : beforeValue) {
                    if ("原始警情".equals(appFireEvent.getFireType())) {
                        primitiveBefore++;
                    }
                    if ("冒烟警情".equals(appFireEvent.getFireType())) {
                        smokingBefore++;
                    }
                    if ("确认警情".equals(appFireEvent.getFireType())) {
                        affirmBefore++;
                    }
                    
                    lossBefore = appFireEvent.getLoss()+lossBefore;
                    hurtBefore = appFireEvent.getHurtNum()+hurtBefore;
                    deadBefore = appFireEvent.getDeadNum()+deadBefore;
                    
                }
                
                
                JSONObject streetResult = new JSONObject();
                streetResult.put("streetName", street.getName());
                
                //原始警情
                streetResult.put("primitiveNow", primitiveNow);
                streetResult.put("primitiveBefore", primitiveBefore);
                if (primitiveBefore==0) {
                    streetResult.put("primitiveYearBefore", 0);
                }else{
                    streetResult.put("primitiveYearBefore", df.format((float)(primitiveNow-primitiveBefore)/primitiveBefore));
                }
                //冒烟警情
                streetResult.put("smokingNow", smokingNow);
                streetResult.put("smokingBefore", smokingBefore);
                if (smokingBefore==0) {
                    streetResult.put("smokingYearBefore", "-");
                }else{
                    streetResult.put("smokingYearBefore", df.format((float)(smokingNow-smokingBefore)/smokingBefore));
                }
                //确认警情
                streetResult.put("affirmNow", affirmNow);
                streetResult.put("affirmBefore", affirmBefore);
                if (affirmBefore==0) {
                    streetResult.put("affirmYearBefore", "-");
                }else{
                    streetResult.put("affirmYearBefore", df.format((float)(affirmNow-affirmBefore)/affirmBefore));
                }
                //损失
                streetResult.put("lossNow", lossNow);
                streetResult.put("lossBefore", lossBefore);
                if (lossBefore==0) {
                    streetResult.put("lossYearBefore", "-");
                }else{
                    streetResult.put("lossYearBefore", df.format((float)(lossNow-lossBefore)/lossBefore));
                }
                //受伤
                streetResult.put("hurtNow", hurtNow);
                streetResult.put("hurtBefore", hurtBefore);
                if (hurtBefore==0) {
                    streetResult.put("hurtYearBefore", "-");
                }else{
                    streetResult.put("hurtYearBefore", df.format((float)(hurtNow-hurtBefore)/hurtBefore));
                }
                //死亡
                streetResult.put("deadNow", deadNow);
                streetResult.put("deadBefore", deadBefore);
                if (deadBefore==0) {
                    streetResult.put("deadYearBefore", "-");
                }else{
                    streetResult.put("deadYearBefore", df.format((float)(deadNow-deadBefore)/deadBefore));
                }
                
                
                
                result.add(streetResult);

            }

        }

        return result;
    }

    @Override
    public List<JSONObject> getAreaDate(Long streetId) {
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
        
        
        Street street = streetRepository.getOne(streetId);

        for (int i = 1; i < 13; i++) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            calendar1.add(calendar.MONTH, -1);// 获取上个月月份
            String[] date1 = sdf.format(calendar1.getTime()).split("-");

            List<AppFireEvent> list = fireEventRepository.findAreaDateToMonth(date1[0], date1[1], street.getName());
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

            JSONObject one = new JSONObject();
            one.put("month", date1[1] + "月");
            one.put("value", oned);

            JSONObject two = new JSONObject();
            two.put("month", date1[1] + "月");
            two.put("value", twod);

            JSONObject three = new JSONObject();
            three.put("month", date1[1] + "月");
            three.put("value", threed);

            oneSum.add(one);
            twoSum.add(two);
            threeSum.add(three);

        }

        JSONObject one = new JSONObject();// 原始警情
        JSONObject two = new JSONObject();// 冒烟警情
        JSONObject three = new JSONObject();// 确认警情

        one.put("type", "原始警情");
        one.put("unit", "起");
        one.put("data", oneSum);

        two.put("type", "冒烟警情");
        two.put("unit", "起");
        two.put("data", twoSum);

        three.put("type", "确认警情");
        three.put("unit", "起");
        three.put("data", threeSum);

        List<JSONObject> result = new ArrayList<JSONObject>();
        result.add(one);
        result.add(two);
        result.add(three);

        return result;
    }

}
