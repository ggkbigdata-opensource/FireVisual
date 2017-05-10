package com.fire.app.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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

    /**
     * @createDate 2017年4月19日下午2:51:57 
     * @author wangzhiwang
     * @param streetId
     * @return 
     * @description
     */
    Street findById(Long streetId);

    /**
     * @createDate 2017年5月10日下午6:54:12 
     * @author wangzhiwang
     * @param reportNum
     * @return 
     * @description
     */
    @Query(value="select * from street s,(select t.street_id from cr_check_report t where t.report_num = ?1) o where o.street_id=s.id", nativeQuery = true)
    Street findByReportNum(String reportNum);

}
