package com.fire.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fire.app.domain.Street;
import com.fire.app.domain.StreetRepository;
import com.fire.app.service.StreetService;

/**
 * @createDate 2017年3月28日下午2:50:29
 * @author wangzhiwang
 * @description
 */
@Service
public class StreetServiceImpl implements StreetService {

    @Autowired
    private StreetRepository streetRepository;

    @Override
    public List<Street> findAll() {
        List<Street> findAll = streetRepository.findAll();
        return findAll;
    }

    @Override
    public Street findById(Long streetId) {
        Street street = streetRepository.findById(streetId);
        return street;
    }

    
}
