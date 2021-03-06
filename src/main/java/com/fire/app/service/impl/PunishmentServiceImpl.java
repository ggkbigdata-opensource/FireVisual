package com.fire.app.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.fire.app.domain.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.fire.app.service.PunishmentService;
import com.fire.app.util.DateUtil;

/**
 * @createDate 2017年3月28日下午4:09:02
 * @author wangzhiwang
 * @description
 */
@Service
public class PunishmentServiceImpl implements PunishmentService {

    @Autowired
    private AppPunishmentRepository punishmentRepository;
    @Autowired
    private StreetRepository streetRepository;
    @Autowired
    private BlockRepository blockRepository;

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

            // 获取临时查封
            List<AppPunishment> seals = punishmentRepository.findSealDateToMonth(date1[0], date1[1]);
            int seald = seals.size();

            // 获取行政执法，行政拘留，刑事拘留，三停
            List<AppPunishment> list = punishmentRepository.findDateToMonth(date1[0], date1[1]);
            int oned = 0;// 行政执法
            int twod = 0;// 行政拘留
            int threed = 0;// 刑事拘留
            int fourd = 0;// 三停
            for (AppPunishment punish : list) {
                if ("行政罚款".equals(punish.getPunishMethod())) {
                    oned++;
                }
                if ("行政拘留".equals(punish.getPunishMethod())) {
                    twod++;
                }
                if ("刑事拘留".equals(punish.getPunishMethod())) {
                    threed++;
                }
                if ("停产停业".equals(punish.getPunishMethod())||"停止使用".equals(punish.getPunishMethod())||"停止施工".equals(punish.getPunishMethod())) {//停产停业，停止施工，停止使用
                    fourd++;
                }
            }

            JSONObject one = new JSONObject();// 行政执法
            one.put("month", date1[0] + date1[1]);
            one.put("value", oned);

            JSONObject two = new JSONObject();// 行政拘留
            two.put("month", date1[0] + date1[1]);
            two.put("value", twod);

            JSONObject three = new JSONObject();// 刑事拘留
            three.put("month", date1[0] + date1[1]);
            three.put("value", threed);

            JSONObject four = new JSONObject(); // 三停
            four.put("month", date1[0] + date1[1]);
            four.put("value", fourd);

            JSONObject five = new JSONObject(); // 临时查封
            five.put("month", date1[0] + date1[1]);
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

        one.put("type", "行政罚款");
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

    @SuppressWarnings("static-access")
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
        List<JSONObject> fourSum = new ArrayList<JSONObject>();
        List<JSONObject> fiveSum = new ArrayList<JSONObject>();

        //
        Street street = streetRepository.getOne(streetId);

        for (int i = 1; i < 13; i++) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            calendar1.add(calendar.MONTH, -1);// 获取上个月月份
            String[] date1 = sdf.format(calendar1.getTime()).split("-");

            // 获取临时查封
            // List<AppPunishment> seals =
            // punishmentRepository.findSealStreetDateToMonth(date1[0],
            // date1[1],street.getName());
            List<AppPunishment> seals = punishmentRepository.findSealStreetDateToMonth(date1[0], date1[1],
                    street.getId());
            int seald = seals.size();

            // 获取行政执法，行政拘留，刑事拘留，三停
            // List<AppPunishment> list =
            // punishmentRepository.findStreetDateToMonth(date1[0],
            // date1[1],street.getName());
            List<AppPunishment> list = punishmentRepository.findStreetDateToMonth(date1[0], date1[1], street.getId());
            int oned = 0;// 行政执法
            int twod = 0;// 行政拘留
            int threed = 0;// 刑事拘留
            int fourd = 0;// 三停
            for (AppPunishment punish : list) {
                if ("行政罚款".equals(punish.getPunishMethod())) {
                    oned++;
                }
                if ("行政拘留".equals(punish.getPunishMethod())) {
                    twod++;
                }
                if ("刑事拘留".equals(punish.getPunishMethod())) {
                    threed++;
                }
                if ("停产停业".equals(punish.getPunishMethod())||"停止使用".equals(punish.getPunishMethod())||"停止施工".equals(punish.getPunishMethod())) {
                    fourd++;
                }
            }

            JSONObject one = new JSONObject();// 行政执法
            one.put("month", date1[0] + date1[1]);
            one.put("value", oned);

            JSONObject two = new JSONObject();// 行政拘留
            two.put("month", date1[0] + date1[1]);
            two.put("value", twod);

            JSONObject three = new JSONObject();// 刑事拘留
            three.put("month", date1[0] + date1[1]);
            three.put("value", threed);

            JSONObject four = new JSONObject(); // 三停
            four.put("month", date1[0] + date1[1]);
            four.put("value", fourd);

            JSONObject five = new JSONObject(); // 临时查封
            five.put("month", date1[0] + date1[1]);
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

        one.put("type", "行政罚款");
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
    public List<JSONObject> getBaseDate(String beginTime, String endTime) {
        // 获取所有的街道
        List<Street> streets = streetRepository.findAll();
        List<JSONObject> result = new ArrayList<JSONObject>();

        if (streets != null && streets.size() > 0) {
            for (Street street : streets) {

                Date bTime = DateUtil.parse(beginTime + "-01");
                Date eTime = DateUtil.parse(endTime + "-31");

                int fineNumNow = 0;// 行政处罚宗数
                double finePriceNow = 0;// 行政处罚
                int admiDetNow = 0;// 行政拘留
                int crimDetNow = 0;// 刑事拘留
                int sealUpNow = 0;// 临时查封
                int ThreeStopNow = 0;// 三停

                // 获取临时查封的数据
                // List<AppPunishment> sealUpValue =
                // punishmentRepository.findSealUpStreetData(bTime,
                // eTime,street.getName());
                List<AppPunishment> sealUpValue = punishmentRepository.findSealUpStreetData(bTime, eTime,
                        street.getId());
                if (sealUpValue != null) {
                    sealUpNow = sealUpValue.size();
                }

                // 获取行政处罚，行政拘留，刑事拘留，三停的数据
                // List<AppPunishment> otherValue =
                // punishmentRepository.findStreetData(bTime, eTime,
                // street.getName());
                List<AppPunishment> otherValue = punishmentRepository.findStreetData(bTime, eTime, street.getId());
                for (AppPunishment punish : otherValue) {
                    if ("行政罚款".equals(punish.getPunishMethod())) {
                        fineNumNow++;// 行政处罚宗数
                        if (StringUtils.isNotEmpty(punish.getFineAmount())) {
                            finePriceNow = Double.parseDouble(punish.getFineAmount()) + finePriceNow;// 行政处罚
                        }
                    }
                    if ("行政拘留".equals(punish.getPunishMethod())) {
                        admiDetNow++;
                    }
                    if ("刑事拘留".equals(punish.getPunishMethod())) {
                        crimDetNow++;
                    }
                    if ("停产停业".equals(punish.getPunishMethod())||"停止使用".equals(punish.getPunishMethod())||"停止施工".equals(punish.getPunishMethod())) {
                        ThreeStopNow++;
                    }

                }

                // 同期

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(bTime);
                calendar.add(Calendar.YEAR, -1);// 当前时间减去一年，即一年前的时间
                bTime = calendar.getTime();

                calendar.setTime(eTime);
                calendar.add(Calendar.YEAR, -1);// 当前时间减去一年，即一年前的时间
                eTime = calendar.getTime();

                int fineNumBefore = 0;// 行政处罚宗数
                double finePriceBefore = 0;// 行政处罚
                int admiDetBefore = 0;// 行政拘留
                int crimDetBefore = 0;// 刑事拘留
                int sealUpBefore = 0;// 临时查封
                int ThreeStopBefore = 0;// 三停

                // 获取临时查封的数据
                // List<AppPunishment> sealUpBeforeValue =
                // punishmentRepository.findSealUpStreetData(bTime,
                // eTime,street.getName());
                List<AppPunishment> sealUpBeforeValue = punishmentRepository.findSealUpStreetData(bTime, eTime,
                        street.getId());
                if (sealUpBeforeValue != null) {
                    sealUpBefore = sealUpBeforeValue.size();
                }

                // 获取行政处罚，行政拘留，刑事拘留，三停的数据
                // List<AppPunishment> otherBeforeValue =
                // punishmentRepository.findStreetData(bTime, eTime,
                // street.getName());
                List<AppPunishment> otherBeforeValue = punishmentRepository.findStreetData(bTime, eTime,
                        street.getId());

                for (AppPunishment punish : otherBeforeValue) {
                    if ("行政罚款".equals(punish.getPunishMethod())) {
                        fineNumBefore++;// 行政处罚宗数
                        if (StringUtils.isNotEmpty(punish.getFineAmount())) {
                            finePriceBefore = Double.parseDouble(punish.getFineAmount()) + finePriceBefore;// 行政处罚
                        }
                    }
                    if ("行政拘留".equals(punish.getPunishMethod())) {
                        admiDetBefore++;
                    }
                    if ("刑事拘留".equals(punish.getPunishMethod())) {
                        crimDetBefore++;
                    }
                    if ("停产停业".equals(punish.getPunishMethod())||"停止使用".equals(punish.getPunishMethod())||"停止施工".equals(punish.getPunishMethod())) {
                        ThreeStopBefore++;
                    }

                }

                JSONObject streetResult = new JSONObject();
                streetResult.put("streetName", street.getName());
                streetResult.put("streetId", street.getId());

                // 临时查封
                streetResult.put("sealUpNow", sealUpNow);
                streetResult.put("sealUpBefore", sealUpBefore);
                // 行政处罚
                streetResult.put("fineNumNow", fineNumNow);
                streetResult.put("fineNumBefore", fineNumBefore);
                streetResult.put("finePriceNow", finePriceNow);
                streetResult.put("finePriceBefore", finePriceBefore);
                // 行政拘留
                streetResult.put("admiDetNow", admiDetNow);
                streetResult.put("admiDetBefore", admiDetBefore);
                // 刑事拘留
                streetResult.put("crimDetNow", crimDetNow);
                streetResult.put("crimDetBefore", crimDetBefore);
                // 三停
                streetResult.put("ThreeStopNow", ThreeStopNow);
                streetResult.put("ThreeStopBefore", ThreeStopBefore);

                result.add(streetResult);

            }

        }

        return result;
    }

    @Override
    public AppPunishment fingById(Long id) {

        AppPunishment punishment = punishmentRepository.findOne(id);
        return punishment;
    }

    @Override
    public List<JSONObject> getBlockData(Long streetId, String beginTime, String endTime) {

        // 获取所有的街道
        List<Block> blocks = blockRepository.findByStreetId(streetId);
        List<JSONObject> result = new ArrayList<JSONObject>();

        for (Block block : blocks) {
            Date bTime = DateUtil.parse(beginTime + "-01");
            Date eTime = DateUtil.parse(endTime + "-31");

            int fineNumNow = 0;// 行政处罚宗数
            double finePriceNow = 0;// 行政处罚
            int admiDetNow = 0;// 行政拘留
            int crimDetNow = 0;// 刑事拘留
            int sealUpNow = 0;// 临时查封
            int ThreeStopNow = 0;// 三停

            List<AppPunishment> sealUpValue = punishmentRepository.findSealUpBlockData(bTime, eTime, block.getId());
            if (sealUpValue != null) {
                sealUpNow = sealUpValue.size();
            }

            // 获取行政处罚，行政拘留，刑事拘留，三停的数据
            List<AppPunishment> otherValue = punishmentRepository.findBlockData(bTime, eTime, block.getId());
            for (AppPunishment punish : otherValue) {
                if ("行政罚款".equals(punish.getPunishMethod())) {
                    fineNumNow++;// 行政处罚宗数
                    if (StringUtils.isNotEmpty(punish.getFineAmount())) {
                        finePriceNow = Double.parseDouble(punish.getFineAmount()) + finePriceNow;// 行政处罚
                    }
                }
                if ("行政拘留".equals(punish.getPunishMethod())) {
                    admiDetNow++;
                }
                if ("刑事拘留".equals(punish.getPunishMethod())) {
                    crimDetNow++;
                }
                if ("停产停业".equals(punish.getPunishMethod())||"停止使用".equals(punish.getPunishMethod())||"停止施工".equals(punish.getPunishMethod())) {
                    ThreeStopNow++;
                }

            }
            JSONObject streetResult = new JSONObject();
            streetResult.put("blockName", block.getName());
            streetResult.put("blockId", block.getId());

            // 临时查封
            streetResult.put("sealUpNow", sealUpNow);
            // 行政处罚
            streetResult.put("fineNumNow", fineNumNow);
            streetResult.put("finePriceNow", finePriceNow);
            // 行政拘留
            streetResult.put("admiDetNow", admiDetNow);
            // 刑事拘留
            streetResult.put("crimDetNow", crimDetNow);
            // 三停
            streetResult.put("ThreeStopNow", ThreeStopNow);

            result.add(streetResult);

        }

        return result;
    }

    @Override
    public JSONObject findByBlockIdAndType(Long blockId, Integer type,String beginTime,String endTime) {
        // type 1--行罚 2--行拘 3--刑拘 4--临封 5--三停（//停产停业、停止使用、停止施工）

        Block block = blockRepository.findOne(blockId);

        if (block == null) {
            throw new RuntimeException("没有找到对应的社区信息");
        }

        Date bTime = DateUtil.parse(beginTime + "-01");
        Date eTime = DateUtil.parse(endTime + "-30");
        
        List<AppPunishment> punishments = null;
        if (type == 1 || type == 2 || type == 3) {
            String punishMehtod = null;
            if (type == 1) {
                punishMehtod = "行政罚款";
            } else if (type == 2) {
                punishMehtod = "行政拘留";
            } else {
                punishMehtod = "刑事拘留";
            }
            punishments = punishmentRepository.findByBlockIdAndPunishMethod(blockId, punishMehtod,bTime,eTime);
        } else if (type == 4) { // 火灾表示确认
            String punishMehtod = "临时查封";
            punishments = punishmentRepository.findSealUpByCondition(blockId, punishMehtod,bTime,eTime);
        } else {
            List<String> methods = new ArrayList<String>();
            methods.add("停产停业");//停产停业、停止使用、停止施工
            methods.add("停止使用");
            methods.add("停止施工");
            punishments = punishmentRepository.findStopDataByCondition(blockId, methods,bTime,eTime);
        }

        List<JSONObject> list = new ArrayList<JSONObject>();

        JSONObject result = new JSONObject();

        result.put("blockName", block.getName());
        result.put("blockId", block);
        result.put("type", type);

        for (AppPunishment punishment : punishments) {
            JSONObject obj = new JSONObject();
            obj.put("id", punishment.getId());
            obj.put("blockName", punishment.getBlockName());

            if (type == 1) {
                obj.put("type_change", "执法类型：行政罚款");
            } else if (type == 2) {
                obj.put("type_change", "执法类型：行政拘留");
            } else if (type == 3) {
                obj.put("type_change", "执法类型：刑事拘留");
            } else if (type == 4) {
                obj.put("type_change", "执法类型：临时查封");
            } else {
                obj.put("type_change", "执法类型：三停");
            }

            obj.put("unitName", punishment.getPunishmentUnitName());
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

        result.put("list", list);
        return result;
    }

    @Override
    public List<JSONObject> getLawEnforcementList(Long streetId, String time, Integer type) {

        // type 1--行罚 2--行拘 3--刑拘 4--临封 5--三停（//停产停业、停止使用、停止施工）
        // type 1--行政罚款  2--行政拘留  3--刑事拘留  4--临时查封  5--三停
        String year = time.substring(0, 4);
        String mounth = time.substring(4, 6);
        
        List<AppPunishment> punishments = null;
        if (type == 1 || type == 2 || type == 3) {
            String punishMehtod = null;
            if (type == 1) {
                punishMehtod = "行政罚款";
            } else if (type == 2) {
                punishMehtod = "行政拘留";
            } else {
                punishMehtod = "刑事拘留";
            }
            punishments = punishmentRepository.findBystreetId(streetId, year,mounth, punishMehtod);
        } else if (type == 4) { // 
            String punishMehtod = "临时查封";
            punishments = punishmentRepository.findSealUpystreetId(streetId, year,mounth, punishMehtod);
        } else {
            List<String> methods = new ArrayList<String>();
            methods.add("停产停业");//停产停业、停止使用、停止施工
            methods.add("停止使用");
            methods.add("停止施工");
            punishments = punishmentRepository.findBystreetId(streetId, year,mounth,methods);
        }

        List<JSONObject> list = new ArrayList<JSONObject>();

        JSONObject result = new JSONObject();

        for (AppPunishment punishment : punishments) {
            JSONObject obj = new JSONObject();
            obj.put("id", punishment.getId());
            obj.put("blockName", punishment.getBlockName());

            if (type == 1) {
                obj.put("type_change", "执法类型: 行政罚款");
            } else if (type == 2) {
                obj.put("type_change", "执法类型: 行政拘留");
            } else if (type == 3) {
                obj.put("type_change", "执法类型: 刑事拘留");
            } else if (type == 4) {
                obj.put("type_change", "执法类型: 临时查封");
            } else {
                obj.put("type_change", "执法类型: 三停");
            }

            obj.put("unitName", punishment.getPunishmentUnitName());
            obj.put("id", punishment.getId());

            Date time1 = null;
            if ("临时查封".equals(punishment.getPunishMethod())) {
                time1 = punishment.getSealUpTimeBegin();
            } else {
                time1 = punishment.getExecuteTime();
            }

            obj.put("time", DateUtil.formatDate(time1, "yyyy/MM/dd HH:mm"));

            obj.put("dutyPerson", punishment.getDutyPersonName());
            // 传上来的参数

            list.add(obj);
        }
        
        return list;
    }

    @Override
    public List<JSONObject> getLawEnforcementList(String time, Integer type) {
     // type 1--行罚 2--行拘 3--刑拘 4--临封 5--三停（//停产停业、停止使用、停止施工）
        // type 1--行政罚款  2--行政拘留  3--刑事拘留  4--临时查封  5--三停
        String year = time.substring(0, 4);
        String mounth = time.substring(4, 6);
        
        List<AppPunishment> punishments = null;
        if (type == 1 || type == 2 || type == 3) {
            String punishMehtod = null;
            if (type == 1) {
                punishMehtod = "行政罚款";
            } else if (type == 2) {
                punishMehtod = "行政拘留";
            } else {
                punishMehtod = "刑事拘留";
            }
            punishments = punishmentRepository.findByPunishMehtod(year,mounth, punishMehtod);
        } else if (type == 4) { // 
            String punishMehtod = "临时查封";
            punishments = punishmentRepository.findSealUpByPunishMehtod(year,mounth, punishMehtod);
        } else {
            List<String> methods = new ArrayList<String>();
            methods.add("停产停业");////停产停业、停止使用、停止施工
            methods.add("停止使用");
            methods.add("停止施工");
            punishments = punishmentRepository.findByPunishMehtod( year,mounth,methods);
        }

        List<JSONObject> list = new ArrayList<JSONObject>();

        JSONObject result = new JSONObject();

        for (AppPunishment punishment : punishments) {
            JSONObject obj = new JSONObject();
            obj.put("id", punishment.getId());
            obj.put("blockName", punishment.getBlockName());

            if (type == 1) {
                obj.put("type_change", "执法类型: 行政罚款");
            } else if (type == 2) {
                obj.put("type_change", "执法类型: 行政拘留");
            } else if (type == 3) {
                obj.put("type_change", "执法类型: 刑事拘留");
            } else if (type == 4) {
                obj.put("type_change", "执法类型: 临时查封");
            } else {
                obj.put("type_change", "执法类型: 三停");
            }

            obj.put("unitName", punishment.getPunishmentUnitName());
            obj.put("id", punishment.getId());

            Date time1 = null;
            if ("临时查封".equals(punishment.getPunishMethod())) {
                time1 = punishment.getSealUpTimeBegin();
            } else {
                time1 = punishment.getExecuteTime();
            }

            obj.put("time", DateUtil.formatDate(time1, "yyyy/MM/dd HH:mm"));

            obj.put("dutyPerson", punishment.getDutyPersonName());
            // 传上来的参数

            list.add(obj);
        }
        
        return list;
    }

}
