package com.fire.app.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.fire.app.domain.AppPunishment;
import com.fire.app.domain.Street;
import com.fire.app.service.PunishmentService;
import com.fire.app.service.StreetService;
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
    @Autowired
    private StreetService streetService;

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

         if (!ContextHolderUtils.isLogin()) { return "login/login"; }
        //type   1--刑罚   2--刑拘   3--刑拘   4--临封   5--三停
        JSONObject result = this.getStreeEventData(streetId, name, type);
        request.setAttribute("result", result);

        return "lawEnforcement/lawEnforcement-fire-list";
    }
    
    @RequestMapping(value = "/searchPunishment" ,method = RequestMethod.GET)
    @ResponseBody
    private JSONObject toSearchPunishment(@RequestParam(required = true)Long streetId, String name,@RequestParam(required = true) Integer type) {
        
        //type   1--刑罚   2--刑拘   3--刑拘   4--临封   5--三停
        JSONObject result = this.getStreeEventData(streetId, name, type);
        
        return result;
    }
    
    @RequestMapping(value = "/punishment" ,method = RequestMethod.GET)
    private String toPunishmentPage(HttpServletRequest request, @RequestParam(required = true)Long id,@RequestParam(required = true)Long type) {
        
        /*
         * if (!ContextHolderUtils.isLogin()) { return "login/login"; }
         */
        
        AppPunishment punishment = punishmentService.fingById(id);
        
        punishment.setPunishMethod(type+"");
        
        request.setAttribute("punishment", punishment);
        
        return "lawEnforcement/lawEnforcement-fire-detail";
    }
    
    private JSONObject getStreeEventData(@RequestParam(required = true)Long streetId, String name,@RequestParam(required = true) Integer type) {

        //type   1--刑罚   2--刑拘   3--刑拘   4--临封   5--三停

        List<AppPunishment> punishments = punishmentService.findByStreetIdAndNameAndType(streetId, name, type);
        List<JSONObject> list = new ArrayList<JSONObject>();

        
        Street street = streetService.findById(streetId);
        if (street==null) {
            throw new RuntimeException("没有找到对应的街道信息");
        }
        
        JSONObject result = new JSONObject();
        
        result.put("streetName", street.getName());
        result.put("type", type);
        
        for (AppPunishment punishment : punishments) {
            JSONObject obj = new JSONObject();
            obj.put("id", punishment.getId());
            obj.put("blockName", punishment.getBlockName());
            
            if (type==1) {
               obj.put("type_change", "行罚");
            }else if (type==2) {
                obj.put("type_change", "行拘");
            }else if (type==3) {
                obj.put("type_change", "刑拘");
            }else if (type==4) {
                obj.put("type_change", "临封");
            }else{
                obj.put("type_change", "三停");
            }
            
            obj.put("UnitName", punishment.getPunishmentUnitName());
            
            Date time = null;
            if ("临时查封".equals(punishment.getPunishMethod())) {
                time=punishment.getSealUpTimeBegin();
            }else{
                time=punishment.getExecuteTime();
            }
            
            obj.put("time", DateUtil.formatDate(time, "yyyy/MM/dd"));

            obj.put("dutyPerson", punishment.getDutyPersonName());
            //传上来的参数

            list.add(obj);
        }

        result.put("list", list);
        

        return result;
    }
}
