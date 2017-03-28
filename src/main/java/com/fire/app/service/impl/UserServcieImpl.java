package com.fire.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fire.app.domain.User;
import com.fire.app.domain.UserRepository;
import com.fire.app.service.UserService;

/**
 * @createDate 2017年3月28日下午2:50:29
 * @author wangzhiwang
 * @description
 */
@Service
public class UserServcieImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Boolean selectByUsernameAndPassword(String name, String phone) {
        
        User user = userRepository.findByUsernameAndPassword(name,phone);
        if (user !=null ) {
            return true;
        }
        return false;
    }

}
