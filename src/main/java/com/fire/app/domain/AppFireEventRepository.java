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

public interface AppFireEventRepository extends JpaRepository<AppFireEvent, Long> {

    /**
     * @createDate 2017年3月28日下午4:54:22 
     * @author wangzhiwang
     * @param string
     * @param string2
     * @return 
     * @description
     */
   // @Query("SELECT * FROM app_fire_event t where YEAR(t.occur_time)=date1 AND MONTH(t.occur_time)=date2")
    //List<AppFireEvent> findDateToMonth(String date1, String date2);

    
}
