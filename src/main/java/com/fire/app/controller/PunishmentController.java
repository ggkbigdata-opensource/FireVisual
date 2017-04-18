package com.fire.app.controller;

import java.util.ArrayList;
import java.util.Date;
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
import com.fire.app.domain.AppPunishment;
import com.fire.app.service.PunishmentService;
import com.fire.app.util.ContextHolderUtils;
import com.fire.app.util.DateUtil;

/**
 * @createDate 2017年3月28日下午3:57:31
 * @author wangzhiwang
 * @description
 */
@RequestMapping("/app/punishment")
@Controller
public class PunishmentController {

    @Autowired
    private PunishmentService punishmentService;

    @RequestMapping("/")
    private String toFireEvent() {

        if (!ContextHolderUtils.isLogin()) {
            return "login/login";
        }

        return "lawEnforcement/lawEnforcement-tianHe";
    }

    @RequestMapping(value = "/main")
    @ResponseBody
    public List<JSONObject> main() {

        /*
         * if (!ContextHolderUtils.isLogin()) { return "login/login"; }
         */
        
        List<JSONObject> result = punishmentService.getData();

        return result;

    }

    @RequestMapping("/toBasePage")
    private String toBasePage() {

        if (!ContextHolderUtils.isLogin()) {
            return "login/login";
        }

        return "lawEnforcement/lawEnforcement-fire";
    }

    /**
     * @createDate 2017年3月29日上午10:14:53
     * @author wangzhiwang
     * @param type
     * @param beginTime
     * @param endTime
     * @return
     * @description 获取每个街道详情
     */
    @RequestMapping(value = "/getBaseDate", method = RequestMethod.POST)
    @ResponseBody
    private List<JSONObject> getBaseDate(String beginTime, String endTime) {

        /*
         * if (!ContextHolderUtils.isLogin()) { return "login/login"; }
         */

        List<JSONObject> result = punishmentService.getBaseDate(beginTime, endTime);

        return result;
    }

    @RequestMapping("/toAreaPage")
    private String toAreaPage() {

        if (!ContextHolderUtils.isLogin()) {
            return "login/login";
        }

        return "lawEnforcement/lawEnforcement-region";
    }

    /**
     * @createDate 2017年3月29日上午10:14:53
     * @author wangzhiwang
     * @param type
     * @param beginTime
     * @param endTime
     * @return
     * @description 获取街道的详情
     */
    @RequestMapping(value = "/getAreaDate", method = RequestMethod.POST)
    @ResponseBody
    private List<JSONObject> getAreaDate(@RequestParam(required = true) Long streetId) {

        /*
         * if (!ContextHolderUtils.isLogin()) { return "login/login"; }
         */

        List<JSONObject> result = punishmentService.getAreaDate(streetId);

        return result;
    }
    
    @RequestMapping(value = "/streePunishment" ,method = RequestMethod.GET)
    private String toStreeEventPage(HttpServletRequest request, @RequestParam(required = true)Long streetId, String name,@RequestParam(required = true) Integer type) {

        /*
         * if (!ContextHolderUtils.isLogin()) { return "login/login"; }
         */
        
        //type   1--刑罚   2--刑拘   3--刑拘   4--临封   5--三停

        List<AppPunishment> punishments = punishmentService.findByStreetIdAndNameAndType(streetId, name, type);
        List<JSONObject> result = new ArrayList<JSONObject>();

        for (AppPunishment punishment : punishments) {
            JSONObject obj = new JSONObject();
            obj.put("id", punishment.getId());
            obj.put("blockName", punishment.getBlockName());
            obj.put("type", punishment.getPunishMethod());
            
            Date time = null;
            if ("临时查封".equals(punishment.getPunishMethod())) {
                time=punishment.getSealUpTimeBegin();
            }else{
                time=punishment.getExecuteTime();
            }
            
            obj.put("time", DateUtil.formatDate(time, "yyyy/MM/dd"));

            //传上来的参数
            obj.put("streetId", streetId);
            obj.put("name", name);
            obj.put("type", type);
            

            result.add(obj);
        }

        request.setAttribute("result", result);

        return "alarm/alarm-fire-list";
    }
}
