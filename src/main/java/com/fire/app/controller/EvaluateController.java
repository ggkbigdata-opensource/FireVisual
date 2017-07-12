package com.fire.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fire.app.util.ContextHolderUtils;

/**
 * @createDate 2017年3月28日下午3:57:31
 * @author wangzhiwang
 * @description
 */
@RequestMapping(value = "/app/evaluate")
@Controller
public class EvaluateController {

    @RequestMapping("/")
    private String toFireEvent() {

        if (!ContextHolderUtils.isLogin()) {
            return "login/login";
        }

        return "evaluation/evaluation";
    }

    
    
}
