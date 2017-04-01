package com.fire.app.domain;

import java.util.Date;
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

public interface AppFireEventRepository extends JpaRepository<AppFireEvent, Long> {

    /**
     * @createDate 2017年3月28日下午4:54:22
     * @author wangzhiwang
     * @param string
     * @param string2
     * @return
     * @description
     */
    @Query(value = "SELECT * FROM app_fire_event t where YEAR(t.occur_time)=?1 AND MONTH(t.occur_time)=?2", nativeQuery = true)
    List<AppFireEvent> findDateToMonth(String date1, String date2);

    /**
     * @createDate 2017年3月29日上午10:37:32 
     * @author wangzhiwang
     * @param beginTime
     * @param endTime
     * @param id
     * @return 
     * @description
     */
    @Query(value = "SELECT * FROM app_fire_event t where t.occur_time between ?1 and ?2 and t.block_name like %?3%", nativeQuery = true)
    List<AppFireEvent> findStreetData(Date beginTime, Date endTime, String streetName);

    /**
     * @createDate 2017年3月29日下午4:42:27 
     * @author wangzhiwang
     * @param string
     * @param string2
     * @param id
     * @return 
     * @description
     */
    @Query(value = "SELECT * FROM app_fire_event t where YEAR(t.occur_time)=?1 AND MONTH(t.occur_time)=?2 and t.block_name like %?3%", nativeQuery = true)
    List<AppFireEvent> findAreaDateToMonth(String string, String string2, String streetName);

   

}
