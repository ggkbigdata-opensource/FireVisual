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

public interface CrCheckReportResultStatRepository extends JpaRepository<CrCheckReportResultStat, Long> {

    /**
     * @createDate 2017年4月1日下午4:06:09 
     * @author wangzhiwang
     * @param itemNumber
     * @return 
     * @description
     */
    List<CrCheckReportResultStat> findByReportNum(String itemNumber);

    /**
     * @createDate 2017年4月1日下午5:14:07 
     * @author wangzhiwang
     * @return 
     * @description
     */
    @Query(value = "select SUM(t.check_num) checkNum,SUM(t.unqualified_num) unqualifiedNum,t.item_code itemCode,t.item_name itemName FROM cr_check_report_result_stat t  GROUP BY t.item_code", nativeQuery = true)
    List<Object> findGroupByItemCode();

   
   

}
