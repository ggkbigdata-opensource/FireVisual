package com.fire.app.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @fileName AppFireEventRepository.java
 * @author csk
 * @createTime 2017年3月28日 下午12:41:14
 * @version 1.0
 * @function
 */

public interface CrCheckReportInfoRepository extends JpaRepository<CrCheckReportInfo, String> {

    /**
     * @createDate 2017年4月1日上午10:29:04 
     * @author wangzhiwang
     * @param id
     * @return 
     * @description
     */
    @Query(value = "SELECT * FROM cr_check_report_info t where t.report_num  like %?1% limit 0,1" , nativeQuery = true)
    CrCheckReportInfo findByReportNum(String reportNum);

   

}
