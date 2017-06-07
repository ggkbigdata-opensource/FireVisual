package com.fire.app.common;

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
@Controller
public class ErrorController {


    @RequestMapping("/500")
    private String serverError() {

        return "error/500";
    }
    
    @RequestMapping("/404")
    private String notFound() {
        
        return "error/404";
    }

    
}
