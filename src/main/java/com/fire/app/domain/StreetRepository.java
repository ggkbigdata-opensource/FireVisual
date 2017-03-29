package com.fire.app.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @fileName BsMainFunctionRepository.java
 * @author csk
 * @createTime 2017年3月28日 下午12:48:35
 * @version 1.0
 * @function
 */

public interface StreetRepository extends JpaRepository<Street, Long> {

    /**
     * @createDate 2017年3月29日下午4:11:26 
     * @author wangzhiwang
     * @param streetName
     * @return 
     * @description
     */
    Street findByName(String streetName);

}
