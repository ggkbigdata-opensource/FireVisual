package com.fire.app.service.impl;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
                //暂时修改
                if ("火灾".equals(event.getFireType())) {
                    threed++;
                }
            }
            
            if (list!=null && list.size()>0) {
                oned=list.size();
            }

            JSONObject one = new JSONObject();
            one.put("month", date1[0]+date1[1]);
            one.put("value", oned);
            
            
            JSONObject two = new JSONObject();
            two.put("month", date1[0]+date1[1]);
            two.put("value", twod);

            JSONObject three = new JSONObject();
            three.put("month", date1[0]+date1[1]);
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
                
               // List<AppFireEvent> nowValue = fireEventRepository.findStreetData(bTime, eTime, street.getName());
                List<AppFireEvent> nowValue = fireEventRepository.findStreetData(bTime, eTime, street.getId());
                
                for (AppFireEvent appFireEvent : nowValue) {
                    if ("原始".equals(appFireEvent.getFireType())) {
                        primitiveNow++;
                    }
                    if ("冒烟".equals(appFireEvent.getFireType())) {
                        smokingNow++;
                    }
                    if ("确认".equals(appFireEvent.getFireType())) {
                        affirmNow++;
                    }
                    //暂时修改
                    if ("火灾".equals(appFireEvent.getFireType())) {
                        affirmNow++;
                    }
                    
                    if (appFireEvent.getLoss()!=null) {
                        lossNow = appFireEvent.getLoss()+lossNow;
                    }
                    if (appFireEvent.getHurtNum()!=null) {
                        hurtNow = appFireEvent.getHurtNum()+hurtNow;
                    }
                    if (appFireEvent.getDeadNum()!=null) {
                        deadNow = appFireEvent.getDeadNum()+deadNow;
                    }
                   
                    
                }
                    //暂时修改
                if (nowValue!=null && nowValue.size()>0) {
                    primitiveNow=nowValue.size();
                }
                
                
                
                // 同比
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(bTime);
                calendar.add(Calendar.YEAR, -1);// 当前时间减去一年，即一年前的时间
                bTime = calendar.getTime();

                calendar.setTime(eTime);
                calendar.add(Calendar.YEAR, -1);// 当前时间减去一年，即一年前的时间
                eTime = calendar.getTime();

               // List<AppFireEvent> beforeValue = fireEventRepository.findStreetData(bTime, eTime, street.getName());
                List<AppFireEvent> beforeValue = fireEventRepository.findStreetData(bTime, eTime, street.getId());
                
                for (AppFireEvent appFireEvent : beforeValue) {
                    if ("原始".equals(appFireEvent.getFireType())) {
                        primitiveBefore++;
                    }
                    if ("冒烟".equals(appFireEvent.getFireType())) {
                        smokingBefore++;
                    }
                    if ("确认".equals(appFireEvent.getFireType())) {
                        affirmBefore++;
                    }
                    //暂时修改
                    if ("火灾".equals(appFireEvent.getFireType())) {
                        affirmBefore++;
                    }
                    
                    
                    if (appFireEvent.getLoss()!=null) {
                        lossBefore = appFireEvent.getLoss()+lossBefore;
                    }
                    if (appFireEvent.getHurtNum()!=null) {
                        hurtBefore = appFireEvent.getHurtNum()+hurtBefore;
                    }
                    if (appFireEvent.getDeadNum()!=null) {
                        deadBefore = appFireEvent.getDeadNum()+deadBefore;
                    }
                    
                }
                //暂时修改
                if (beforeValue!=null && beforeValue.size()>0) {
                    primitiveBefore=beforeValue.size();
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
                
                streetResult.put("id", street.getId());
                
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

           // List<AppFireEvent> list = fireEventRepository.findAreaDateToMonth(date1[0], date1[1], street.getName());
            List<AppFireEvent> list = fireEventRepository.findAreaDateToMonth(date1[0], date1[1], street.getId());
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
                //暂时更改
                if ("火灾".equals(event.getFireType())) {
                    threed++;
                }
            }
            
            if (list!=null && list.size()>0) {
                oned=list.size();
            }

            
            
            JSONObject one = new JSONObject();
            one.put("month", date1[0]+date1[1]);
            one.put("value", oned);

            JSONObject three = new JSONObject();
            three.put("month",date1[0]+date1[1]);
            three.put("value", threed);
            
            JSONObject two = new JSONObject();
            two.put("month", date1[0]+date1[1]);
            two.put("value", twod);

            

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
    public List<AppFireEvent> getStreetEvent(Long streetId) {

        List<AppFireEvent> result = fireEventRepository.findByStreetId(streetId);
        
        return result;
    }

    @Override
    public AppFireEvent getEventById(Long id) {
        AppFireEvent result = fireEventRepository.findOne(id);
        return result;
    }

    @Override
    public List<AppFireEvent> findByStreetIdAndNameAndType(Long streetId, String name, Integer type) {
        //type   1--原始   2--冒烟   3--确认   4--损失   5--受伤   6--死亡
       
        List<AppFireEvent> result = null;
        if (type==1) {//原始表示所有
            if (StringUtils.isEmpty(name)) {
                result = fireEventRepository.findByStreetId(streetId);
            }else{
                result= fireEventRepository.findBystreetIdAndBlockName(streetId,name);
            }
        }else if(type==2||type==3) {  //火灾表示确认
            String fireType=null;
            if (type==2) {
                fireType="冒烟";
            }else {
                fireType="火灾";
            }
            
            if (StringUtils.isEmpty(name)) {
                result= fireEventRepository.findBystreetIdAndFireType(streetId,fireType);
            }else{
                result= fireEventRepository.findBystreetIdAndBlockName(streetId,name,fireType);
            }
            
        }else if(type==4||type==5||type==6) {
            if (type==4) {
                result= fireEventRepository.findByLossIsNotNull(streetId);
            }else if (type==5){
                result= fireEventRepository.findByHurtNumIsNotNull(streetId);
            }else {
                result= fireEventRepository.findByDeadNumIsNotNull(streetId);
            }
        }
        return result;
    }

    @Override
    public AppFireEvent findEventById(Long id) {
        AppFireEvent event = fireEventRepository.findOne(id);
        return event;
    }

    @Override
    public List<JSONObject> getBlockData(Long streetId,String beginTime, String endTime,Integer type) {

        List<JSONObject> result = new ArrayList<JSONObject>();
     // 获取社区
        List<Block> blocks = blockRepository.findByStreetId(streetId);
        
        for (Block block : blocks) {
            Date bTime = DateUtil.parse(beginTime + "-01");
            Date eTime = DateUtil.parse(endTime + "-31");

            int primitiveNow = 0;
            int smokingNow = 0;
            int affirmNow = 0;
            
            double lossNow = 0;
            int hurtNow = 0;
            int deadNow = 0;
            
            
         // type 1--原始 2--冒烟 3--确认 4--损失 5--受伤 6--死亡
            List<AppFireEvent> nowValue = null;
            if (type==1) {//原始表示所有
                nowValue = fireEventRepository.findByBlockId(bTime, eTime, block.getId());
            }else if(type==2||type==3) {  //火灾表示确认
                String fireType=null;
                if (type==2) {
                    fireType="冒烟";
                }else {
                    fireType="火灾";
                }
                nowValue = fireEventRepository.findByBlockId(bTime, eTime, block.getId(),fireType);
            }else if(type==4||type==5||type==6) {
                if (type==4) {
                    nowValue = fireEventRepository.findByBlockIdAndLossIsNotNull(bTime, eTime, block.getId());
                }else if (type==5){
                    nowValue = fireEventRepository.findByBlockIdAndHurtNumIsNotNull(bTime, eTime, block.getId());
                }else {
                    nowValue = fireEventRepository.findByBlockIdAndDeadNumIsNotNull(bTime, eTime, block.getId());
                }
            }else if (type==0) {
               List<String> types = new ArrayList<>();
               types.add("冒烟");
               types.add("火灾");
                nowValue = fireEventRepository.findByBlockId(bTime, eTime, block.getId(),types);
                
            }
            
            for (AppFireEvent appFireEvent : nowValue) {
                if ("原始".equals(appFireEvent.getFireType())) {
                    primitiveNow++;
                }
                if ("冒烟".equals(appFireEvent.getFireType())) {
                    smokingNow++;
                }
                if ("确认".equals(appFireEvent.getFireType())) {
                    affirmNow++;
                }
                //暂时修改
                if ("火灾".equals(appFireEvent.getFireType())) {
                    affirmNow++;
                }
                
                if (appFireEvent.getLoss()!=null) {
                    lossNow = appFireEvent.getLoss()+lossNow;
                }
                if (appFireEvent.getHurtNum()!=null) {
                    hurtNow = appFireEvent.getHurtNum()+hurtNow;
                }
                if (appFireEvent.getDeadNum()!=null) {
                    deadNow = appFireEvent.getDeadNum()+deadNow;
                }
                
                
                //暂时修改
                if (nowValue!=null && nowValue.size()>0) {
                    primitiveNow=nowValue.size();
                }
                
            }
            JSONObject streetResult = new JSONObject();
            
            //原始警情
            streetResult.put("primitiveNow", primitiveNow);
            //冒烟警情
            streetResult.put("smokingNow", smokingNow);
            //确认警情
            streetResult.put("affirmNow", affirmNow);
            //损失
            streetResult.put("lossNow", lossNow);
            //受伤
            streetResult.put("hurtNow", hurtNow);
            //死亡
            streetResult.put("deadNow", deadNow);
            //社区
            streetResult.put("blockId", block.getId());
            streetResult.put("blockName", block.getName());
            
            result.add(streetResult);
                

        }

        return result;
    }

    @Override
    public JSONObject getBlockEvent(Long blockId, Integer type,String beginTime, String endTime) {

        Block block = blockRepository.findOne(blockId);
        if (block == null) {
            throw new RuntimeException("没有找到对应的社区");
        }
        Date bTime = DateUtil.parse(beginTime + "-01");
        Date eTime = DateUtil.parse(endTime + "-30");
      //type   1--原始   2--冒烟   3--确认   4--损失   5--受伤   6--死亡
        
        List<AppFireEvent> events = null;
        if (type==1) {//原始表示所有
            events = fireEventRepository.findByBlockId(bTime, eTime, blockId);
        }else if(type==2||type==3) {  //火灾表示确认
            String fireType=null;
            if (type==2) {
                fireType="冒烟";
            }else {
                fireType="火灾";
            }
            events= fireEventRepository.findByBlockId(bTime, eTime, blockId, fireType);
            
        }else if(type==4||type==5||type==6) {
            if (type==4) {
                events= fireEventRepository.findByBlockIdAndLossIsNotNull(bTime, eTime, blockId);
            }else if (type==5){
                events= fireEventRepository.findByBlockIdAndHurtNumIsNotNull(bTime, eTime, blockId);
            }else {
                events= fireEventRepository.findByBlockIdAndDeadNumIsNotNull(bTime, eTime, blockId);
            }
        }else if (type==0) {
            List<String> types = new ArrayList<>();
            types.add("冒烟");
            types.add("火灾");
            events= fireEventRepository.findByBlockId(bTime, eTime, blockId, types);
        }
        
        JSONObject result = new JSONObject();
        result.put("blockName", block.getName());
        result.put("blockId", blockId);
        result.put("type", type);

        List<JSONObject> list = new ArrayList<JSONObject>();

        for (AppFireEvent event : events) {
            JSONObject obj = new JSONObject();

            String time = null;
            if (event.getOccurTime() != null) {
                time = DateUtil.formatDate(event.getOccurTime(), "yyyy/MM/dd HH:mm");
            }
            obj.put("time", time);
            obj.put("id", event.getId());

            if (type == 2) {
                obj.put("type_change", "警情类型：冒烟");
            } else if (type == 3) {
                obj.put("type_change", "警情类型：确认");
            } else if (type == 4) {
                obj.put("type_change", "损失：" + event.getLoss());
            } else if (type == 5) {
                obj.put("type_change", "受伤：" + event.getHurtNum());
            } else if (type == 6) {
                obj.put("type_change", "死亡：" + event.getDeadNum());
            } else if (type == 0) {
                if ("冒烟".equals(event.getFireType())) {
                    obj.put("type_change", "警情类型：冒烟");
                }else{
                    obj.put("type_change", "警情类型：确认");
                }
            } else {
                obj.put("type_change", "警情类型：原始");
            }

            list.add(obj);
        }
        result.put("list", list);

        return result;
        
    }

    @Override
    public List<JSONObject> getRegionList(Long streetId, String time, Integer type) {
        Street street = streetRepository.findOne(streetId);
        if (street == null) {
            throw new RuntimeException("没有找到对应的街道");
        }
        String year = time.substring(0, 4);
        String month = time.substring(4, 6);
      //type   1--原始   2--冒烟   3--确认   4--损失   5--受伤   6--死亡
        
        List<AppFireEvent> events = null;
        if (type==1) {//原始表示所有
            events = fireEventRepository.findByStreetId(streetId,year,month);
        }else if(type==2||type==3) {  //火灾表示确认
            String fireType=null;
            if (type==2) {
                fireType="冒烟";
            }else {
                fireType="火灾";
            }
            events= fireEventRepository.findByStreetId(streetId, year, month,fireType);
            
        }else if(type==4||type==5||type==6) {
            if (type==4) {
                events= fireEventRepository.findByStreetIdAndLossIsNotNull(year, month, streetId);
            }else if (type==5){
                events= fireEventRepository.findByStreetIdAndHurtNumIsNotNull(year, month, streetId);
            }else {
                events= fireEventRepository.findByStreetIdAndDeadNumIsNotNull(year, month, streetId);
            }
        }
        
        
        List<JSONObject> list = new ArrayList<JSONObject>();

        for (AppFireEvent event : events) {
            JSONObject obj = new JSONObject();

            String time1 = null;
            if (event.getOccurTime() != null) {
                time1 = DateUtil.formatDate(event.getOccurTime(), "yyyy/MM/dd HH:mm");
            }
            obj.put("time", time1);
            obj.put("id", event.getId());

            if (type == 2) {
                obj.put("type_change", "警情类型：冒烟");
            } else if (type == 3) {
                obj.put("type_change", "警情类型：确认");
            } else if (type == 4) {
                obj.put("type_change", "损失：" + event.getLoss());
            } else if (type == 5) {
                obj.put("type_change", "受伤：" + event.getHurtNum());
            } else if (type == 6) {
                obj.put("type_change", "死亡：" + event.getDeadNum());
            } else {
                obj.put("type_change", "警情类型：原始");
            }

            list.add(obj);
        }
        
        return list;
    }

    @Override
    public List<JSONObject> getRegionList(String time, Integer type) {
        String year = time.substring(0, 4);
        String month = time.substring(4, 6);
      //type   1--原始   2--冒烟   3--确认   4--损失   5--受伤   6--死亡
        
        List<AppFireEvent> events = null;
        if (type==1) {//原始表示所有
            events = fireEventRepository.findByTime(year,month);
        }else if(type==2||type==3) {  //火灾表示确认
            String fireType=null;
            if (type==2) {
                fireType="冒烟";
            }else {
                fireType="火灾";
            }
            events= fireEventRepository.findByFireType( year, month,fireType);
            
        }else if(type==4||type==5||type==6) {
            if (type==4) {
                events= fireEventRepository.findByFireTypeAndLossIsNotNull(year, month);
            }else if (type==5){
                events= fireEventRepository.findByFireTypeAndHurtNumIsNotNull(year, month);
            }else {
                events= fireEventRepository.findByFireTypeAndDeadNumIsNotNull(year, month);
            }
        }
        
        
        List<JSONObject> list = new ArrayList<JSONObject>();

        for (AppFireEvent event : events) {
            JSONObject obj = new JSONObject();

            String time1 = null;
            if (event.getOccurTime() != null) {
                time1 = DateUtil.formatDate(event.getOccurTime(), "yyyy/MM/dd HH:mm");
            }
            obj.put("time", time1);
            obj.put("id", event.getId());

            if (type == 2) {
                obj.put("type_change", "警情类型：冒烟");
            } else if (type == 3) {
                obj.put("type_change", "警情类型：确认");
            } else if (type == 4) {
                obj.put("type_change", "损失：" + event.getLoss());
            } else if (type == 5) {
                obj.put("type_change", "受伤：" + event.getHurtNum());
            } else if (type == 6) {
                obj.put("type_change", "死亡：" + event.getDeadNum());
            } else {
                obj.put("type_change", "警情类型：原始");
            }

            list.add(obj);
        }
        
        return list;
    }

}
