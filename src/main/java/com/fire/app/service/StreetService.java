package com.fire.app.service;

import java.util.List;

import com.fire.app.domain.Street;

/**
 * @createDate 2017年3月28日下午4:08:47
 * @author wangzhiwang
 * @description
 */
public interface StreetService {

    /**
     * @createDate 2017年3月29日下午5:00:33 
     * @author wangzhiwang
     * @return 
     * @description
     */
    List<Street> findAll();

    /**
     * @createDate 2017年4月19日下午2:51:16 
     * @author wangzhiwang
     * @param streetId
     * @return 
     * @description
     */
    Street findById(Long streetId);

}
