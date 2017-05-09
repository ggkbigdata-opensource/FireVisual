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

    @Query(value = "SELECT * FROM app_punishment t where YEAR(t.execute_time)=?1 AND MONTH(t.execute_time)=?2 and t.punish_method != '临时查封'", nativeQuery = true)
    List<AppPunishment> findDateToMonth(String string, String string2);

    @Query(value = "SELECT * FROM app_punishment t where YEAR(t.seal_up_time_begin)=?1 AND MONTH(t.seal_up_time_begin)=?2 and t.punish_method = '临时查封'", nativeQuery = true)
    List<AppPunishment> findSealDateToMonth(String string, String string2);

    @Query(value = "SELECT * FROM app_punishment t where t.seal_up_time_begin between ?1 and ?2 and t.punish_method = '临时查封' and t.street_id =?3", nativeQuery = true)
    List<AppPunishment> findSealUpStreetData(Date bTime, Date eTime, Long streetId);

    @Query(value = "SELECT * FROM app_punishment t where t.execute_time between ?1 and ?2 and t.punish_method != '临时查封' and t.street_id=?3", nativeQuery = true)
    List<AppPunishment> findStreetData(Date bTime, Date eTime, Long streetId);

    @Query(value = "SELECT * FROM app_punishment t where YEAR(t.seal_up_time_begin)=?1 AND MONTH(t.seal_up_time_begin)=?2 and t.punish_method = '临时查封' and t.street_id=?3", nativeQuery = true)
    List<AppPunishment> findSealStreetDateToMonth(String year, String month, Long streetId);

    @Query(value = "SELECT * FROM app_punishment t where YEAR(t.execute_time)=?1 AND MONTH(t.execute_time)=?2 and t.punish_method != '临时查封' and t.street_id=?3", nativeQuery = true)
    List<AppPunishment> findStreetDateToMonth(String year, String month, Long streetId);

    @Query(value = "SELECT * FROM app_punishment t where YEAR(t.execute_time)=?1 AND MONTH(t.execute_time)=?2 and t.street_id=?3", nativeQuery = true)
    List<AppPunishment> findAllDataByTime(Date newYear, Date nowTime, Long streetId);

    @Query(value = "SELECT * FROM app_punishment t where t.street_id = ?1 and t.punish_method = ?2 order by execute_time desc", nativeQuery = true)
    List<AppPunishment> findBystreetIdAndPunishMethod(Long streetId, String punishMehtod);

    @Query(value = "SELECT * FROM app_punishment t where t.street_id = ?1 and t.punish_method = ?2 and t.punishment_unit_name like %?3% order by execute_time desc", nativeQuery = true)
    List<AppPunishment> findBystreetIdAndPunishMethodAndBlockName(Long streetId, String punishMehtod, String name);

    @Query(value = "SELECT * FROM app_punishment t where t.block_id = ?1 and t.punish_method = ?2 and t.seal_up_time_begin between ?3 and ?4 order by seal_up_time_begin desc", nativeQuery = true)
    List<AppPunishment> findSealUpByCondition(Long blockId, String punishMehtod, Date bTime, Date eTime);

    @Query(value = "SELECT * FROM app_punishment t where t.block_id = ?1 and t.punish_method in ?2 and t.execute_time between ?1 and ?2 order by execute_time desc", nativeQuery = true)
    List<AppPunishment> findStopDataByCondition(Long blockId, List<String> methods, Date bTime, Date eTime);

    @Query(value = "SELECT * FROM app_punishment t where t.seal_up_time_begin between ?1 and ?2 and t.punish_method = '临时查封' and t.block_id=?3", nativeQuery = true)
    List<AppPunishment> findSealUpBlockData(Date bTime, Date eTime, Long id);
   
    @Query(value = "SELECT * FROM app_punishment t where t.execute_time between ?1 and ?2 and t.punish_method != '临时查封' and t.block_id=?3", nativeQuery = true)
    List<AppPunishment> findBlockData(Date bTime, Date eTime, Long id);

    @Query(value = "SELECT * FROM app_punishment t where t.block_id = ?1 and t.punish_method = ?2 and t.execute_time between ?3 and ?4 order by execute_time desc", nativeQuery = true)
    List<AppPunishment> findByBlockIdAndPunishMethod(Long bloclId, String punishMehtod, Date bTime, Date eTime);

    @Query(value = "select * from app_punishment t where t.street_id =?1 and year(execute_time) = ?2  and month(execute_time) = ?3 and t.punish_method =?4 order by execute_time desc", nativeQuery = true)
    List<AppPunishment> findBystreetId(Long streetId, String year, String mounth, String punishMehtod);

    @Query(value = "select * from app_punishment t where t.street_id =?1 and year(seal_up_time_begin) = ?2  and month(seal_up_time_begin) = ?3 and t.punish_method =?4 order by seal_up_time_begin desc", nativeQuery = true)
    List<AppPunishment> findSealUpystreetId(Long streetId, String year, String mounth, String punishMehtod);

    @Query(value = "SELECT * FROM app_punishment t where t.street_id = ?1 and year(execute_time) = ?2  and month(execute_time) = ?3 and t.punish_method in ?4 order by execute_time desc", nativeQuery = true)
    List<AppPunishment> findBystreetId(Long streetId, String year, String mounth, List<String> methods);

    @Query(value = "select * from app_punishment t where year(execute_time) = ?1  and month(execute_time) = ?2 and t.punish_method =?3 order by execute_time desc", nativeQuery = true)
    List<AppPunishment> findByPunishMehtod(String year, String mounth, String punishMehtod);

    @Query(value = "select * from app_punishment t where year(seal_up_time_begin) = ?1  and month(seal_up_time_begin) = ?2 and t.punish_method =?3 order by seal_up_time_begin desc", nativeQuery = true)
    List<AppPunishment> findSealUpByPunishMehtod(String year, String mounth, String punishMehtod);

    @Query(value = "SELECT * FROM app_punishment t where year(execute_time) = ?1 and month(execute_time) = ?2 and t.punish_method in ?3 order by execute_time desc", nativeQuery = true)
    List<AppPunishment> findByPunishMehtod(String year, String mounth, List<String> methods);

}
