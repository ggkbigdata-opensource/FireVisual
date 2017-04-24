package com.fire.app.domain;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @fileName AppPunishmentRepository.java
 * @author csk
 * @createTime 2017年3月28日 下午12:41:53
 * @version 1.0
 * @function
 */

public interface AppPunishmentRepository extends JpaRepository<AppPunishment, Long> {

    /**
     * @createDate 2017年3月30日下午3:48:35 
     * @author wangzhiwang
     * @param string
     * @param string2
     * @param seal
     * @return 
     * @description 查询不是临时查封的数据
     */
    @Query(value = "SELECT * FROM app_punishment t where YEAR(t.execute_time)=?1 AND MONTH(t.execute_time)=?2 and t.punish_method != '临时查封'", nativeQuery = true)
    List<AppPunishment> findDateToMonth(String string, String string2);

    /**
     * @createDate 2017年3月30日下午3:52:01 
     * @author wangzhiwang
     * @param string
     * @param string2
     * @return 
     * @description 查找临时查封的数据
     */
    @Query(value = "SELECT * FROM app_punishment t where YEAR(t.seal_up_time_begin)=?1 AND MONTH(t.seal_up_time_begin)=?2 and t.punish_method = '临时查封'", nativeQuery = true)
    List<AppPunishment> findSealDateToMonth(String string, String string2);

    /**
     * @createDate 2017年3月30日下午5:15:15 
     * @author wangzhiwang
     * @param bTime
     * @param eTime
     * @param id
     * @return 
     * @description  查询每个街道临时查封的数据
     */     
    //@Query(value = "SELECT * FROM app_punishment t where t.seal_up_time_begin between ?1 and ?2 and t.punish_method = '临时查封' and t.block_name like %?3%", nativeQuery = true)
   // List<AppPunishment> findSealUpStreetData(Date bTime, Date eTime, String streetName);
    @Query(value = "SELECT * FROM app_punishment t where t.seal_up_time_begin between ?1 and ?2 and t.punish_method = '临时查封' and t.street_id =?3", nativeQuery = true)
    List<AppPunishment> findSealUpStreetData(Date bTime, Date eTime, Long streetId);

    /**
     * @createDate 2017年3月30日下午5:25:58 
     * @author wangzhiwang
     * @param bTime
     * @param eTime
     * @param id
     * @return 
     * @description 获取出来临时查封之外的数据
     */
   // @Query(value = "SELECT * FROM app_punishment t where t.execute_time between ?1 and ?2 and t.punish_method != '临时查封' and t.block_name like %?3%", nativeQuery = true)
    //List<AppPunishment> findStreetData(Date bTime, Date eTime, String streetName);
    @Query(value = "SELECT * FROM app_punishment t where t.execute_time between ?1 and ?2 and t.punish_method != '临时查封' and t.street_id=?3", nativeQuery = true)
    List<AppPunishment> findStreetData(Date bTime, Date eTime, Long streetId);

    /**
     * @createDate 2017年3月30日下午8:23:53 
     * @author wangzhiwang
     * @param string
     * @param string2
     * @param streetName
     * @return 
     * @description
     */
    //@Query(value = "SELECT * FROM app_punishment t where YEAR(t.seal_up_time_begin)=?1 AND MONTH(t.seal_up_time_begin)=?2 and t.punish_method = '临时查封' and t.block_name like %?3%", nativeQuery = true)
   // List<AppPunishment> findSealStreetDateToMonth(String year, String month, String streetName);
    @Query(value = "SELECT * FROM app_punishment t where YEAR(t.seal_up_time_begin)=?1 AND MONTH(t.seal_up_time_begin)=?2 and t.punish_method = '临时查封' and t.street_id=?3", nativeQuery = true)
    List<AppPunishment> findSealStreetDateToMonth(String year, String month, Long streetId);

    /**
     * @createDate 2017年3月30日下午8:23:59 
     * @author wangzhiwang
     * @param string
     * @param string2
     * @param streetName
     * @return 
     * @description
     */
    //@Query(value = "SELECT * FROM app_punishment t where YEAR(t.execute_time)=?1 AND MONTH(t.execute_time)=?2 and t.punish_method != '临时查封' and t.block_name like %?3%", nativeQuery = true)
    //List<AppPunishment> findStreetDateToMonth(String year, String month, String streetName);
    @Query(value = "SELECT * FROM app_punishment t where YEAR(t.execute_time)=?1 AND MONTH(t.execute_time)=?2 and t.punish_method != '临时查封' and t.street_id=?3", nativeQuery = true)
    List<AppPunishment> findStreetDateToMonth(String year, String month, Long streetId);

    /**
     * @createDate 2017年3月31日上午11:04:43 
     * @author wangzhiwang
     * @param newYear
     * @param nowTime
     * @param name 
     * @description   查询时间段里面对应街道的所有执法数据
     */
   // @Query(value = "SELECT * FROM app_punishment t where YEAR(t.execute_time)=?1 AND MONTH(t.execute_time)=?2 and t.block_name like %?3%", nativeQuery = true)
    //List<AppPunishment> findAllDataByTime(Date newYear, Date nowTime, String name);
    @Query(value = "SELECT * FROM app_punishment t where YEAR(t.execute_time)=?1 AND MONTH(t.execute_time)=?2 and t.street_id=?3", nativeQuery = true)
    List<AppPunishment> findAllDataByTime(Date newYear, Date nowTime, Long streetId);

    /**
     * @createDate 2017年4月18日下午5:50:24 
     * @author wangzhiwang
     * @param streetId
     * @param punishMehtod
     * @return 
     * @description
     */
    @Query(value = "SELECT * FROM app_punishment t where t.street_id = ?1 and t.punish_method = ?2 order by execute_time desc", nativeQuery = true)
    List<AppPunishment> findBystreetIdAndPunishMethod(Long streetId, String punishMehtod);

    /**
     * @createDate 2017年4月18日下午5:51:03 
     * @author wangzhiwang
     * @param streetId
     * @param punishMehtod
     * @param name
     * @return 
     * @description
     */
    @Query(value = "SELECT * FROM app_punishment t where t.street_id = ?1 and t.punish_method = ?2 and t.punishment_unit_name like %?3% order by execute_time desc", nativeQuery = true)
    List<AppPunishment> findBystreetIdAndPunishMethodAndBlockName(Long streetId, String punishMehtod, String name);

    /**
     * @createDate 2017年4月18日下午5:59:31 
     * @author wangzhiwang
     * @param streetId
     * @param punishMehtod
     * @return 
     * @description
     */
    @Query(value = "SELECT * FROM app_punishment t where t.street_id = ?1 and t.punish_method = ?2 order by seal_up_time_begin desc", nativeQuery = true)
    List<AppPunishment> findSealUpByCondition(Long streetId, String punishMehtod);
    @Query(value = "SELECT * FROM app_punishment t where t.street_id = ?1 and t.punish_method = ?3 and t.punishment_unit_name like %?2% order by seal_up_time_begin desc", nativeQuery = true)
    List<AppPunishment> findSealUpByCondition(Long streetId, String name, String punishMehtod);

    /**
     * @createDate 2017年4月18日下午6:02:09 
     * @author wangzhiwang
     * @param streetId
     * @param methods
     * @return 
     * @description
     */
    @Query(value = "SELECT * FROM app_punishment t where t.street_id = ?1 and t.punish_method in ?2 order by execute_time desc", nativeQuery = true)
    List<AppPunishment> findStopDataByCondition(Long streetId, List<String> methods);
    @Query(value = "SELECT * FROM app_punishment t where t.street_id = ?1 and t.punish_method in ?2 and t.punishment_unit_name like %?3% order by execute_time desc", nativeQuery = true)
    List<AppPunishment> findStopDataByCondition(Long streetId, List<String> methods, String name);

    /**
     * @createDate 2017年4月19日上午9:12:29 
     * @author wangzhiwang
     * @param id
     * @return 
     * @description
     */
    AppPunishment findById(Long id);

    /**
     * @createDate 2017年4月24日上午10:05:40 
     * @author wangzhiwang
     * @param bTime
     * @param eTime
     * @param id
     * @return 
     * @description
     */
    @Query(value = "SELECT * FROM app_punishment t where t.execute_time between ?1 and ?2 and t.punish_method = '临时查封' and t.block_id=?3", nativeQuery = true)
    List<AppPunishment> findSealUpBlockData(Date bTime, Date eTime, Long id);
    @Query(value = "SELECT * FROM app_punishment t where t.execute_time between ?1 and ?2 and t.punish_method != '临时查封' and t.block_id=?3", nativeQuery = true)
    List<AppPunishment> findBlockData(Date bTime, Date eTime, Long id);

}
