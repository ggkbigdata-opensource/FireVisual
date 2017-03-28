package com.fire.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @fileName TestController.java
 * @author csk
 * @createTime 2017年3月28日 下午2:25:32
 * @version 1.0
 * @function
 */
@Controller
public class TestController {

    @RequestMapping(value="alarmFire")
    public String TestPage(){
        
        return "alarm/alarm-fire";
    }
}
