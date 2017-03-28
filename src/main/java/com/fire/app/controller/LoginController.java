package com.fire.app.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fire.app.common.App;
import com.fire.app.common.CommonResult;
import com.fire.app.domain.User;
import com.fire.app.service.UserService;
import com.fire.app.util.AppHelper;
import com.fire.app.util.ContextHolderUtils;

@Controller
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String login() {

        return "login/login";

    }
    
    @RequestMapping("/index")
    public String index() {
       ContextHolderUtils.isLogin("index/index");
        return null;
    }
  
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult loginToPhone(@RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "phone", required = true) String phone,
            @RequestParam(value = "verifCode", required = true) String verifCode, HttpSession session) {

        session.removeAttribute(App.USER_SESSION_KEY);

        phone = AppHelper.encryptPassword(phone);
        Boolean flag = this.userService.selectByUsernameAndPassword(name,phone);

        if (flag) {
            return CommonResult.success("success");
        }
        
        return CommonResult.fail("帐号或密码错误");
        
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        if (session != null) {
            session.invalidate();
        }

        return "redirect:/";
    }

    @RequestMapping("checkSession")
    @ResponseBody
    public CommonResult checkSession(HttpSession session) {

        if (session.getAttribute(App.USER_SESSION_KEY) != null) {
            return CommonResult.success();
        }

        return CommonResult.fail();
    }

}
