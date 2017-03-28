package com.fire.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fire.app.domain.AppFireEventRepository;
import com.fire.app.service.FireEventServcie;

/**
 * @createDate 2017年3月28日下午4:09:02
 * @author wangzhiwang
 * @description
 */
@Service
public class FireEventServcieImpl implements FireEventServcie {

    @Autowired
    private AppFireEventRepository fireEventRepository;
    
}
