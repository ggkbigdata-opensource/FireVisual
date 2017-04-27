package com.fire.app.controller;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.fire.app.domain.AppFireEvent;
import com.fire.app.domain.Street;
import com.fire.app.service.FireEventService;
import com.fire.app.service.StreetService;
import com.fire.app.util.ContextHolderUtils;
import com.fire.app.util.DateUtil;

/**
 * @createDate 2017年3月28日下午3:57:31
 * @author wangzhiwang
 * @description
 */
@RequestMapping(value = "/app/fireEvent")
@Controller
public class FireEventController {

    @Autowired
    private FireEventService fireEventServcie;
    @Autowired
    private StreetService streetService;

    @RequestMapping("/")
    private String toFireEvent() {

        if (!ContextHolderUtils.isLogin()) {
            return "login/login";
        }

        return "alarm/alarm-tianHe";
    }

    @RequestMapping(value = "/main")
    @ResponseBody
    public List<JSONObject> main() {

        List<JSONObject> result = fireEventServcie.getData();

        return result;

    }

    @RequestMapping("/toBasePage")
    private String toBasePage() {

        /*
         * if (!ContextHolderUtils.isLogin()) { return "login/login"; }
         */

        return "alarm/alarm-fire";
    }

    /**
     * @createDate 2017年3月29日上午10:14:53
     * @author wangzhiwang
     * @param
     * @param beginTime
     * @param endTime
     * @return
     * @description 获取每个街道详情
     */
    @RequestMapping(value = "/getBaseData", method = RequestMethod.POST)
    @ResponseBody
    private List<JSONObject> getBaseDate(String beginTime, String endTime) {

        /*
         * if (!ContextHolderUtils.isLogin()) { return "login/login"; }
         */

        List<JSONObject> result = fireEventServcie.getBaseDate(beginTime, endTime);

        return result;
    }

    @RequestMapping("/toAreaPage")
    private String toAreaPage() {

        /*
         * if (!ContextHolderUtils.isLogin()) { return "login/login"; }
         */

        return "alarm/alarm-region";
    }

    /**
     * @createDate 2017年3月29日上午10:14:53
     * @author wangzhiwang
     * @return
     * @description 获取街道的详情
     */
    @RequestMapping(value = "/getAreaData", method = RequestMethod.POST)
    @ResponseBody
    private List<JSONObject> getAreaDate(@RequestParam(required = true) Long id) {

        /*
         * if (!ContextHolderUtils.isLogin()) { return "login/login"; }
         */

        List<JSONObject> result = fireEventServcie.getAreaDate(id);

        return result;
    }

    @RequestMapping(value = "/streeEvent", method = RequestMethod.GET)
    public String toStreeEventPage(HttpServletRequest request, @RequestParam(required = true) Long streetId,
            String name, @RequestParam(required = true) Integer type) {

        if (!ContextHolderUtils.isLogin()) {
            return "login/login";
        }

        // type 1--原始 2--冒烟 3--确认 4--损失 5--受伤 6--死亡

        List<AppFireEvent> events = fireEventServcie.findByStreetIdAndNameAndType(streetId, name, type);

        Street street = streetService.findById(streetId);

        if (street == null) {
            throw new RuntimeException("没有找到对应的街道");
        }

        JSONObject result = new JSONObject();
        result.put("streetName", street.getName());
        result.put("streetId", streetId);
        result.put("type", type);

        List<JSONObject> list = new ArrayList<JSONObject>();

        for (AppFireEvent event : events) {
            JSONObject obj = new JSONObject();
            obj.put("id", event.getId());
            obj.put("blockName", event.getBlockName());

            String time = null;
            if (event.getOccurTime() != null) {
                time = DateUtil.formatDate(event.getOccurTime(), "yyyy/MM/dd HH:mm");
            }
            obj.put("time", time);

            // 传上来的参数
            obj.put("name", name);

            if (type == 2) {
                obj.put("type_change", "损失：冒烟");
            } else if (type == 3) {
                obj.put("type_change", "损失：确认");
            } else if (type == 4) {
                obj.put("type_change", "损失：" + event.getLoss());
            } else if (type == 5) {
                obj.put("type_change", "受伤：" + event.getHurtNum());
            } else if (type == 6) {
                obj.put("type_change", "死亡：" + event.getDeadNum());
            } else {
                obj.put("type_change", "种类：原始");
            }

            list.add(obj);
        }
        result.put("list", list);

        request.setAttribute("result", result);

        return "alarm/alarm-fire-list";
    }
    
    
    @RequestMapping(value = "/blockEvent", method = RequestMethod.GET)
    public String toBlockEventPage(HttpServletRequest request, @RequestParam(required = true) Long blockId,
            @RequestParam(required = true) Integer type,
            String beginTime, String endTime
            ) {

        if (!ContextHolderUtils.isLogin()) {
            return "login/login";
        }
        
        // type 1--原始 2--冒烟 3--确认 4--损失 5--受伤 6--死亡

        JSONObject result = fireEventServcie.getBlockEvent(blockId, type,beginTime,endTime);

        request.setAttribute("result", result);

        return "alarm/alarm-fire-list";
    }
    

    /**
     * @createDate 2017年4月17日上午9:56:22
     * @author wangzhiwang
     * @param streetId
     * @return
     * @description 查询街道下所有警情
     */
    @RequestMapping(value = "/getStreetEvent", method = RequestMethod.GET)
    @ResponseBody
    private List<JSONObject> getStreetEvent(@RequestParam(required = true) Long streetId) {

        /*
         * if (!ContextHolderUtils.isLogin()) { return "login/login"; }
         */

        List<AppFireEvent> events = fireEventServcie.getStreetEvent(streetId);
        List<JSONObject> result = new ArrayList<JSONObject>();
        for (AppFireEvent event : events) {
            JSONObject obj = new JSONObject();
            obj.put("id", event.getId());
            obj.put("occurTime", event.getOccurTime());
            obj.put("streetName", event.getStreetName());
            obj.put("blockName", event.getBlockName());

            result.add(obj);

        }

        return result;
    }

    /**
     * @createDate 2017年4月17日上午9:56:39
     * @author wangzhiwang
     * @param id
     * @return
     * @description 通过id获取警情数据
     */
    @RequestMapping(value = "/getEvent", method = RequestMethod.GET)
    @ResponseBody
    private AppFireEvent getEvent(@RequestParam(required = true) Long id) {

        AppFireEvent result = fireEventServcie.getEventById(id);

        return result;
    }

    /**
     * @createDate 2017年3月29日上午10:14:53
     * @author wangzhiwang
     * @return
     * @description 获取街道的详情
     */
    @RequestMapping(value = "/event")
    private String getEventById(HttpServletRequest request, @RequestParam(required = true) Long id,
            @RequestParam(required = true) Integer type) {

        AppFireEvent event = fireEventServcie.findEventById(id);

        event.setFireType(type + "");

        request.setAttribute("event", event);

        return "alarm/alarm-fire-detail";
    }


    /**
     * @createDate 2017年3月29日上午10:14:53
     * @author wangzhiwang
     * @return
     * @description 点击对应的街道，跳转页面
     */
    @RequestMapping(value = "/toBlockDataPage")
    private String toBlockDataPage() {

        if (!ContextHolderUtils.isLogin()) {
            return "login/login";
        }

        return "alarm/alarm-block-list";
    }
    
    /**
     * @createDate 2017年3月29日上午10:14:53
     * @author wangzhiwang
     * @return
     * @description 点击对应的街道，显示所有社区的数据
     */
    @RequestMapping(value = "/getBlockData")
    @ResponseBody
    private JSONObject getBlockData(HttpServletRequest request, @RequestParam(required = true) Long streetId,String beginTime, String endTime,
            @RequestParam(required = true) Integer type
            ) {

        // type 1--原始 2--冒烟 3--确认 4--损失 5--受伤 6--死亡
        
        List<JSONObject> result = fireEventServcie.getBlockData(streetId,beginTime, endTime,type);
        
        Street street = streetService.findById(streetId);
        
        JSONObject obj = new JSONObject();
        
        obj.put("streetId", streetId);
        obj.put("streetName", street.getName());
        obj.put("list", result);

        return obj;
    }
    
    /**
     * @createDate 2017年3月29日上午10:14:53
     * @author wangzhiwang
     * @return
     * @description 点击街道警情趋势的街道内容，跳转页面
     */
    @RequestMapping(value = "/toRegionListPage")
    private String toRegionListPage() {return "alarm/alarm-region-list";}
    /**
     * @createDate 2017年3月29日上午10:14:53
     * @author wangzhiwang
     * @return
     * @description 点击对应的街道，显示所有社区的数据
     */
    @RequestMapping(value = "/getRegionList")
    @ResponseBody
    private JSONObject getRegionList(HttpServletRequest request, @RequestParam(required = true) Long streetId,String time,
            @RequestParam(required = true) Integer type
            ) {

        // type 1--原始 2--冒烟 3--确认 4--损失 5--受伤 6--死亡
        
        List<JSONObject> result = fireEventServcie.getRegionList(streetId, time,type);
        
        Street street = streetService.findById(streetId);
        
        JSONObject obj = new JSONObject();
        
        obj.put("streetId", streetId);
        obj.put("streetName", street.getName());
        obj.put("list", result);

        return obj;
    }
    
    
}
