package com.fire.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.fire.app.service.PunishmentService;
import com.fire.app.util.ContextHolderUtils;

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
    private List<JSONObject> getAreaDate(@RequestParam(required = true) Long id) {

        /*
         * if (!ContextHolderUtils.isLogin()) { return "login/login"; }
         */

        List<JSONObject> result = punishmentService.getAreaDate(id);

        return result;
    }
}