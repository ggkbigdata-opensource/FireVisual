package com.fire.app.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fire.app.common.App;
import com.fire.app.common.CommonResult;
import com.fire.app.service.UserService;
import com.fire.app.util.AppHelper;
import com.fire.app.util.ContextHolderUtils;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    static final Logger LOG = Logger.getLogger(LoginController.class);
    
    @RequestMapping("/")
    public String login() {

        return "login/login";

    }
    
    @RequestMapping("app/index")
    public String index() {
       if (ContextHolderUtils.isLogin()) {
           return "index/index";
       }
        return "login";
    }
  
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult loginToPhone(@RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "phone", required = true) String phone,
            @RequestParam(value = "verifCode", required = true) String verifCode, HttpSession session) {

        session.removeAttribute(App.USER_SESSION_KEY);

        verifCode = AppHelper.encryptPassword(verifCode);
        Boolean flag = this.userService.selectByUsernameAndPassword(name,phone,verifCode);
        
        
        
        //-------------------------------------------
       // userService.updateDate();
        
        if (flag) {
            
            LOG.info("帐号"+name+"登录成功");
            return CommonResult.success("success");
        }
        LOG.info("帐号"+name+"登录失败");
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
