package com.fire.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @RequestMapping()
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
    
    @RequestMapping(value = "/blockPunish" ,method = RequestMethod.GET)
    private String toStreeEventPage(HttpServletRequest request, @RequestParam(required = true)Long blockId,@RequestParam(required = true) Integer type,
            String beginTime ,String endTime
            ) {

         if (!ContextHolderUtils.isLogin()) { return "login/login"; }
      //type   1--刑罚   2--刑拘   3--刑拘   4--临封   5--三停

        JSONObject result = punishmentService.findByBlockIdAndType(blockId, type,beginTime,endTime);
        
        request.setAttribute("result", result);

        return "lawEnforcement/lawEnforcement-fire-list";
    }
    
    
    @RequestMapping(value = "/punish" ,method = RequestMethod.GET)
    private String toPunishmentPage(HttpServletRequest request, @RequestParam(required = true)Long id,@RequestParam(required = true)Long type) {
        
        /*
         * if (!ContextHolderUtils.isLogin()) { return "login/login"; }
         */
        
        AppPunishment punishment = punishmentService.fingById(id);
        
        punishment.setPunishMethod(type+"");
        
        request.setAttribute("punishment", punishment);
        
        return "lawEnforcement/lawEnforcement-fire-detail";
    }
    
 
    /**
     * @createDate 2017年4月27日上午9:34:46 
     * @author wangzhiwang
     * @return 
     * @description 击对应的街道，跳转页面
     */
    @RequestMapping(value = "/toBlockDataPage")
    private String toBlockDataPage() {

        if (!ContextHolderUtils.isLogin()) {
            return "login/login";
        }

        return "lawEnforcement/lawEnforcement-block-list";
    }

    /**
     * @createDate 2017年3月29日上午10:14:53
     * @author wangzhiwang
     * @return
     * @description 点击对应的街道，显示所有社区的数据
     */
    @RequestMapping(value = "/getBlockData")
    @ResponseBody
    private JSONObject getBlockData(HttpServletRequest request, @RequestParam(required = true) Long streetId,String beginTime, String endTime
    ) {
        Street street = streetService.findById(streetId);
        
        if (street==null&&"".equals(street)) {
            throw new RuntimeException("没有找到对应的街道信息");
        }
        
        List<JSONObject> result = punishmentService.getBlockData(streetId,beginTime, endTime);
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
    @RequestMapping(value = "/toLawEnforcementListPage")
    private String toRegionListPage() {return "lawEnforcement/lawEnforcement-region-list";}
    /**
     * @createDate 2017年3月29日上午10:14:53
     * @author wangzhiwang
     * @return
     * @description 点击对应的街道，显示所有社区的数据
     */
    @RequestMapping(value = "/getLawEnforcementList" ,method=RequestMethod.POST)
    @ResponseBody
    private JSONObject getLawEnforcementList(@RequestParam(required = true) Long streetId,String time,
            @RequestParam(required = true) Integer type
            ) {

        // type 1--行政罚款  2--行政拘留  3--刑事拘留  4--临时查封  5--三停
        JSONObject obj = new JSONObject();
        List<JSONObject> result = null;
        if (streetId!=null&&!"".equals(streetId)) {
            Street street = streetService.findById(streetId);
            
            if (street == null) {
                throw new RuntimeException("没有找到对应的社区信息");
            }
            //查询街道的下的数据
            result = punishmentService.getLawEnforcementList(streetId, time,type);
            obj.put("streetId", streetId);
            obj.put("streetName", street.getName());
            
        }else {
            //查询全区下的数据
            result = punishmentService.getLawEnforcementList(time,type);
        }
        
        obj.put("list", result);

        return obj;
    }
}
