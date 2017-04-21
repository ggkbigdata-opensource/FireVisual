package com.fire.app.service.impl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fire.app.common.App;
import com.fire.app.domain.User;
import com.fire.app.domain.UserRepository;
import com.fire.app.service.UserService;
import com.fire.app.util.ContextHolderUtils;

/**
 * @createDate 2017年3月28日下午2:50:29
 * @author wangzhiwang
 * @description
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Boolean selectByUsernameAndPassword(String name, String phone) {
        
        User user = userRepository.findByUsernameAndPassword(name,phone);
        if (user !=null ) {
            HttpSession session = ContextHolderUtils.getSession();
            session.setAttribute(App.USER_SESSION_KEY, user);
            return true;
        }
        return false;
    }

    @Override
    public Boolean selectByUsernameAndPassword(String name, String phone, String verifCode) {
        User user = userRepository.findByUsernameAndPassword(name,verifCode);
        if (user !=null ) {
            
            String mobile = user.getMobile();
            if (phone.equals(mobile)) {
                HttpSession session = ContextHolderUtils.getSession();
                session.setAttribute(App.USER_SESSION_KEY, user);
                return true;
            }
            return false;
        }
        return false;
    }

}
