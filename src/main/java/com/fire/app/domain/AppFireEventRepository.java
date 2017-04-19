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
    @Query(value = "SELECT * FROM app_fire_event t where t.occur_time between ?1 and ?2 and t.street_id = ?3", nativeQuery = true)
    List<AppFireEvent> findStreetData(Date beginTime, Date endTime, Long streetId);

    /**
     * @createDate 2017年3月29日下午4:42:27 
     * @author wangzhiwang
     * @param string
     * @param string2
     * @param id
     * @return 
     * @description
     */
    //@Query(value = "SELECT * FROM app_fire_event t where YEAR(t.occur_time)=?1 AND MONTH(t.occur_time)=?2 and t.block_name like %?3%", nativeQuery = true)
   // List<AppFireEvent> findAreaDateToMonth(String string, String string2, String streetName);
    @Query(value = "SELECT * FROM app_fire_event t where YEAR(t.occur_time)=?1 AND MONTH(t.occur_time)=?2 and t.street_id = ?3", nativeQuery = true)
    List<AppFireEvent> findAreaDateToMonth(String string, String string2, Long streetId);

    /**
     * @createDate 2017年4月17日上午9:37:49 
     * @author wangzhiwang
     * @param streetId
     * @return 
     * @description
     */
    @Query(value = "SELECT * FROM app_fire_event t where t.street_id = ?1 order by occur_time desc", nativeQuery = true)
    List<AppFireEvent> findByStreetId(Long streetId);

    /**
     * @createDate 2017年4月17日上午10:04:26 
     * @author wangzhiwang
     * @param id
     * @return 
     * @description
     */
    AppFireEvent findById(Long id);

    /**
     * @createDate 2017年4月18日下午3:24:56 
     * @author wangzhiwang
     * @param streetId
     * @param name
     * @param fireType
     * @return 
     * @description
     */
    @Query(value = "SELECT * FROM app_fire_event t where t.street_id = ?1 and t.fire_type = ?3 and t.block_name like %?2% order by occur_time desc", nativeQuery = true)
    List<AppFireEvent> findBystreetIdAndBlockName(Long streetId, String name, String fireType);

    /**
     * @createDate 2017年4月18日下午3:32:52 
     * @author wangzhiwang
     * @return 
     * @description
     */
    @Query(value = "SELECT * FROM app_fire_event t where t.loss is not null order by occur_time desc", nativeQuery = true)
    List<AppFireEvent> findByLossIsNotNull();

    /**
     * @createDate 2017年4月18日下午3:32:59 
     * @author wangzhiwang
     * @return 
     * @description
     */
    @Query(value = "SELECT * FROM app_fire_event t where t.hurt_num is not null order by occur_time desc", nativeQuery = true)
    List<AppFireEvent> findByHurtNumIsNotNull();

    /**
     * @createDate 2017年4月18日下午3:33:03 
     * @author wangzhiwang
     * @return 
     * @description
     */
    @Query(value = "SELECT * FROM app_fire_event t where t.dead_num is not null order by occur_time desc", nativeQuery = true)
    List<AppFireEvent> findByDeadNumIsNotNull();

    /**
     * @createDate 2017年4月18日下午3:42:03 
     * @author wangzhiwang
     * @return 
     * @description
     */
    @Query(value = "SELECT * FROM app_fire_event  order by occur_time desc", nativeQuery = true)
    List<AppFireEvent> findAllData();

    /**
     * @createDate 2017年4月18日下午4:17:38 
     * @author wangzhiwang
     * @param streetId
     * @param fireType
     * @return 
     * @description
     */
    @Query(value = "SELECT * FROM app_fire_event t where t.street_id = ?1 and t.fire_type = ?2 order by occur_time desc", nativeQuery = true)
    List<AppFireEvent> findBystreetIdAndFireType(Long streetId, String fireType);

    /**
     * @createDate 2017年4月18日下午4:20:27 
     * @author wangzhiwang
     * @param streetId
     * @param name
     * @return 
     * @description
     */
    @Query(value = "SELECT * FROM app_fire_event t where t.street_id = ?1 and t.block_name like %?2% order by occur_time desc", nativeQuery = true)
    List<AppFireEvent> findBystreetIdAndBlockName(Long streetId, String name);

   

}
