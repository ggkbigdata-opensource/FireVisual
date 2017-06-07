package com.fire.app.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @fileName AppFireEventRepository.java
 * @author csk
 * @createTime 2017年3月28日 下午12:41:14
 * @version 1.0
 * @function
 */

public interface CrCheckReportRepository extends JpaRepository<CrCheckReport, String> {

    /**
     * @createDate 2017年6月5日下午7:53:31 
     * @author wangzhiwang
     * @param itemNumber
     * @return 
     * @description
     */
    CrCheckReport findByReportNum(String itemNumber);

    
   

}
